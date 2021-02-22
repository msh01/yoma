package com.github.yoma.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.order.dao.BetonMoneyTotalDao;
import com.github.yoma.order.domain.BetonMoneyTotal;
import com.github.yoma.order.dto.BetonMoneyTotalQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 商砼收付款累计 业务层
 *
 * @author 马世豪
 * @version 2020-12-16
 */
@Service
@Transactional(readOnly = true)
public class BetonMoneyTotalService extends CrudService<BetonMoneyTotalDao, BetonMoneyTotal> {

    @Override
    public BetonMoneyTotal get(Long id) {
        return super.get(id);
    }

    public List<BetonMoneyTotal> findList(BetonMoneyTotalQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BetonMoneyTotal> findPage(BetonMoneyTotalQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BetonMoneyTotalQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BetonMoneyTotal betonMoneyTotal) {
        super.save(betonMoneyTotal);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BetonMoneyTotal betonMoneyTotal) {
        return super.delete(betonMoneyTotal);
    }

}
