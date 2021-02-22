package com.github.yoma.order.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.order.domain.BetonMoneyTotal;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 商砼收付款累计DAO接口
 *
 * @author 马世豪
 * @version 2020-12-16
 */
@Repository
public interface BetonMoneyTotalDao extends CrudDao<BetonMoneyTotal> {

}
