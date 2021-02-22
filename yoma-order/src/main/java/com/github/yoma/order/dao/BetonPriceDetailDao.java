package com.github.yoma.order.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.order.domain.BetonPriceDetail;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 砼标号价格清单DAO接口
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@Repository
public interface BetonPriceDetailDao extends CrudDao<BetonPriceDetail> {

}
