package com.github.yoma.order.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.order.domain.BetonReceiving;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 商砼销售收款明细DAO接口
 *
 * @author 马世豪
 * @version 2020-12-09
 */
@Repository
public interface BetonReceivingDao extends CrudDao<BetonReceiving> {

}
