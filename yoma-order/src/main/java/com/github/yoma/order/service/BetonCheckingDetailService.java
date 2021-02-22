package com.github.yoma.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonCheckingDetailDao;
import com.github.yoma.order.domain.BetonCheckingDetail;
import com.github.yoma.order.dto.BetonCheckingDetailQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 对账明细 业务层
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Service
@Transactional(readOnly = true)
public class BetonCheckingDetailService extends CrudService<BetonCheckingDetailDao, BetonCheckingDetail> {

    @Override
    public BetonCheckingDetail get(Long id) {
        return super.get(id);
    }

    public List<BetonCheckingDetail> findList(BetonCheckingDetailQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonCheckingDetail> findPage(BetonCheckingDetailQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public BetonCheckingDetail createBetonCheckingDetail(BetonCheckingDetailQueryDTO queryDTO) {
        queryDTO.setCreateId(1L);
        queryDTO.setType(0);
        return dao.createBetonCheckingDetail(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonCheckingDetailQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonCheckingDetail betonCheckingDetail) {
        super.save(betonCheckingDetail);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonCheckingDetail betonCheckingDetail) {
        return super.delete(betonCheckingDetail);
    }

}
