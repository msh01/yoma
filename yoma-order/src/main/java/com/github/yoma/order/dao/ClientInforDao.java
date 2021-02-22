package com.github.yoma.order.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.order.domain.ClientInfor;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 搅拌站登录用户DAO接口
 *
 * @author 马世豪
 * @version 2020-12-03
 */
@Repository
public interface ClientInforDao extends CrudDao<ClientInfor> {

}
