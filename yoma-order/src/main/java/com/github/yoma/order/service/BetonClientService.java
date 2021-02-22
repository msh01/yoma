package com.github.yoma.order.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonClientDao;
import com.github.yoma.order.domain.BetonClient;
import com.github.yoma.order.dto.BetonClientQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.common.utils.SandConstants;
import com.github.yoma.common.utils.StringUtils;

/**
 * 商砼客户 业务层
 *
 * @author 马世豪
 * @version 2020-11-20
 */
@Service
@Transactional(readOnly = true)
public class BetonClientService extends CrudService<BetonClientDao, BetonClient> {

    public HashMap<String, String> getAllMap() {
        BetonClientQueryDTO queryDTO = new BetonClientQueryDTO();
        List<BetonClient> list = this.findList(queryDTO);
        HashMap<String, String> textValueMap = new HashMap<>();
        list.forEach(x -> {
            String text = x.getClientName();
            Long value = x.getId();
            textValueMap.put(text, value.toString());
        });
        return textValueMap;
    }

    public Long getValueByText(String text) {
        String s = this.getAllMap().get(text);
        Long value = 0L;
        if (StringUtils.isEmpty(s)) {
            throw new RuntimeException("不存在" + text + "的客户，请在系统录入后重新导入！");
        } else {
            value = Long.valueOf(s);
        }
        return value;
    }

    @Override
    public BetonClient get(Long id) {
        return super.get(id);
    }

    public List<BetonClient> findList(BetonClientQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonClient> findPage(BetonClientQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonClientQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonClient betonClient) {
        betonClient.setCheckType(1);
        betonClient.setClientType(1);
        betonClient.setClientId(SandConstants.SELF_STATION_ID);
        betonClient.setSex(1);
        betonClient.setCreateId(1L);
        betonClient.setState(1);
        betonClient.setCreateTime(LocalDateTime.now());
        betonClient.setUpdateTime(LocalDateTime.now());
        super.save(betonClient);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonClient betonClient) {
        return super.delete(betonClient);
    }

}
