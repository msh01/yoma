package com.github.yoma.core.dao;

import com.github.yoma.core.dto.BaseUsersRolesQueryDTO;
import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseUsersRoles;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 用户角色关联DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseUsersRolesDao extends CrudDao<BaseUsersRoles> {
    public int deleteByUserId(BaseUsersRolesQueryDTO baseUsersRolesQueryDTO);
}
