package com.github.yoma.order.dao;

import com.github.yoma.order.domain.BetonOrder;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 商砼运输订单DAO接口
 * @author 马世豪
 * @version 2020-11-19
 */
@Repository
public interface BetonOrderDao extends CrudDao<BetonOrder> {

}
