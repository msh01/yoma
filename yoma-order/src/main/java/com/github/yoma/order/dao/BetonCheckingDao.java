package com.github.yoma.order.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.order.domain.BetonChecking;
import com.github.yoma.order.dto.BetonCheckingQueryDTO;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 对账明细DAO接口
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Repository
public interface BetonCheckingDao extends CrudDao<BetonChecking> {
    BetonChecking createBetonChecking(BetonCheckingQueryDTO queryDTO);
}
