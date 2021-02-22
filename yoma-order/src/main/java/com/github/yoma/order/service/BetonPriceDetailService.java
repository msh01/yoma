package com.github.yoma.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonPriceDetailDao;
import com.github.yoma.order.domain.BetonPriceDetail;
import com.github.yoma.order.dto.BetonPriceDetailQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 砼标号价格清单 业务层
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@Service
@Transactional(readOnly = true)
public class BetonPriceDetailService extends CrudService<BetonPriceDetailDao, BetonPriceDetail> {

    @Override
    public BetonPriceDetail get(Long id) {
        return super.get(id);
    }

    public List<BetonPriceDetail> findList(BetonPriceDetailQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonPriceDetail> findPage(BetonPriceDetailQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonPriceDetailQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonPriceDetail betonPriceDetail) {
        super.save(betonPriceDetail);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonPriceDetail betonPriceDetail) {
        return super.delete(betonPriceDetail);
    }

}
