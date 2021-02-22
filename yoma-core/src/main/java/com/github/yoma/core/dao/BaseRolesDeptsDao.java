package com.github.yoma.core.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseRolesDepts;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 角色部门关联DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseRolesDeptsDao extends CrudDao<BaseRolesDepts> {

}
