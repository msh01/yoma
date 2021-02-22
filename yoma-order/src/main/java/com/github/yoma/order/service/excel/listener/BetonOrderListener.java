package com.github.yoma.order.service.excel.listener;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月09日 10:30:00
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.github.yoma.order.businessenum.AuditStatusEnum;
import com.github.yoma.order.businessenum.EffectiveStatusEnum;
import com.github.yoma.order.dao.BetonOrderDao;
import com.github.yoma.order.domain.BetonOrder;
import com.github.yoma.order.dto.BetonOrderQueryDTO;
import com.github.yoma.order.vo.BetonOrderVo;
import com.github.yoma.tools.utils.GenerateNum;
import com.github.yoma.common.utils.SandConstants;
import com.github.yoma.common.utils.StringUtils;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class BetonOrderListener extends AnalysisEventListener<BetonOrderVo> {
    /**
     * 每隔5条存储数据库，实际 使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;

    /**
     * 这个集合用于接收 读取Excel文件得到的数据
     */
    List<BetonOrder> list = new ArrayList<BetonOrder>();
    Long equipmentProject = 0L;

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private com.github.yoma.order.dao.BetonOrderDao dao;

    public BetonOrderListener() {

    }

    /**
     *
     * 不要使用自动装配 在测试类中将dao当参数传进来
     */
    public BetonOrderListener(BetonOrderDao dao, Long equipmentProject) {
        this.dao = dao;
        this.equipmentProject = equipmentProject;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     */
    @Override
    public void invoke(BetonOrderVo element, AnalysisContext context) {

        ReadSheetHolder readSheetHolder = context.readSheetHolder();
        Integer sheetNo = readSheetHolder.getSheetNo();
        if (sheetNo == 0) {
            String orderNoERP = element.getOrderNoERP();
            if (StringUtils.isNotEmpty(orderNoERP)) {
                BetonOrderQueryDTO BetonOrderQueryDTO = new BetonOrderQueryDTO();
                BetonOrderQueryDTO.setOrderNoERP(orderNoERP);
                List<BetonOrder> queryList = dao.findList(BetonOrderQueryDTO);
                if (!queryList.isEmpty()) {
                    throw new RuntimeException("当前项目下，orderNoERP " + orderNoERP + " 重复");
                }
                Integer auditStatus = element.getAuditStatus();
                Integer effectiveStatus = element.getEffectiveStatus();
                if (auditStatus != null && effectiveStatus != null) {
                    if (AuditStatusEnum.Audited.code.equals(auditStatus)
                        && EffectiveStatusEnum.efficient.code.equals(effectiveStatus)) {
                        BetonOrder betonOrder = new BetonOrder();
                        BeanUtil.copyProperties(element, betonOrder);
                        // 判断砼强度是否以砂浆结尾。是砂浆结尾标记砂浆，否则标记为混凝土。同时把砂浆字符替换为空
                        String conStrength = betonOrder.getConStrength();
                        Integer trait = null;
                        if (StringUtils.endsWith(conStrength, "砂浆")) {
                            betonOrder.setTrait(2);
                            String replace = conStrength.replace("砂浆", "");
                            betonOrder.setConStrength(replace);
                        } else {
                            betonOrder.setTrait(1);
                        }
                        // 设置默认值
                        betonOrder.setCreateTime(LocalDateTime.now());
                        betonOrder.setUpdateTime(LocalDateTime.now());
                        betonOrder.setState(1);
                        betonOrder.setSource(1);
                        betonOrder.setFinanceType(0);
                        betonOrder.setTallyType(0);
                        betonOrder.setCompanyName(SandConstants.SELF_STATION_NAME);
                        betonOrder.setClientId(SandConstants.SELF_STATION_ID);
                        betonOrder.setCreateId(1L);
                        betonOrder.setCreateName("admin");

                        String generateOrder = GenerateNum.getInstance().GenerateOrder();
                        betonOrder.setOrderNo(generateOrder);
                        this.list.add(betonOrder);
                        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                        if (this.list.size() >= BATCH_COUNT) {
                            saveData(this.list);
                            // 存储完成清理 list
                            this.list.clear();
                        }
                    }

                }

            }
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("解析完毕");
        saveData(list);
    }

    /**
     * 加上存储数据库
     */
    private void saveData(List<BetonOrder> list) {
        // 在这个地方可以调用dao 我们就直接打印数据了
        if (list.isEmpty()) {
            log.info("当前为空excel，未导入任何数据");
        } else {
            this.dao.batchInsert(list);
        }
    }
}
