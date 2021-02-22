package com.github.yoma.order.service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.github.yoma.order.service.excel.converter.LocalDateTimeConverter;
import com.github.yoma.order.vo.BetonOrderVo;
import com.github.yoma.common.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonOrderDao;
import com.github.yoma.order.domain.BetonOrder;
import com.github.yoma.order.dto.BetonOrderQueryDTO;
import com.github.yoma.order.service.excel.listener.BetonOrderListener;
import com.github.yoma.common.persistence.CrudService;

/**
 * 商砼运输订单 业务层
 *
 * @author 马世豪
 * @version 2020-11-19
 */
@Service
@Transactional(readOnly = true)
public class BetonOrderService extends CrudService<BetonOrderDao, BetonOrder> {

    @Override
    public BetonOrder get(Long id) {
        return super.get(id);
    }

    public List<BetonOrder> findList(BetonOrderQueryDTO queryDTO) {
        UserDetails currentUser = SecurityUtils.getCurrentUser();
        return super.findList(queryDTO);
    }

    public PageInfo<BetonOrder> findPage(BetonOrderQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonOrderQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    public void importExcel(BetonOrder betonOrder) throws Exception {
        String fileBaseUrl = "http://img.dev.x-code.top/";
        String fileID = betonOrder.getFileId();
        String remoteUrl = fileBaseUrl + fileID;

        // Long equipmentProject = iotEquipment.getEquipmentProject();
        URL url = null;
        InputStream is = null;
        url = new URL(remoteUrl);
        is = url.openStream();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        /**
         * 参数1 要读取的文件 参数2 要读取的数据对应的实体类类对象 参数3 监听器对象 可以在创建的时候把dao当做参数传进去
         */
        EasyExcel.read(is, BetonOrderVo.class, new BetonOrderListener(this.dao, 1L)).headRowNumber(1)
            .registerConverter(new LocalDateTimeConverter()).autoTrim(true).sheet().doRead();

    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonOrder betonOrder) {
        super.save(betonOrder);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonOrder betonOrder) {
        return super.delete(betonOrder);
    }

}
