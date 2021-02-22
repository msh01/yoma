package com.github.yoma.core.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseUser;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 系统用户DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseUserDao extends CrudDao<BaseUser> {

}
