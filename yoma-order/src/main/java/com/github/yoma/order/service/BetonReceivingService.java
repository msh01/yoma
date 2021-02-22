package com.github.yoma.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonReceivingDao;
import com.github.yoma.order.domain.BetonReceiving;
import com.github.yoma.order.dto.BetonReceivingQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 商砼销售收款明细 业务层
 *
 * @author 马世豪
 * @version 2020-12-09
 */
@Service
@Transactional(readOnly = true)
public class BetonReceivingService extends CrudService<BetonReceivingDao, BetonReceiving> {

    @Override
    public BetonReceiving get(Long id) {
        return super.get(id);
    }

    public List<BetonReceiving> findList(BetonReceivingQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonReceiving> findPage(BetonReceivingQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonReceivingQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonReceiving betonReceiving) {
        super.save(betonReceiving);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonReceiving betonReceiving) {
        return super.delete(betonReceiving);
    }

}
