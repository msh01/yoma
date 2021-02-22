package com.github.yoma.core.dao;

import com.github.yoma.core.dto.BaseRolesMenusQueryDTO;
import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseRolesMenus;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 角色菜单关联DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseRolesMenusDao extends CrudDao<BaseRolesMenus> {
    public int deleteByRoleId(BaseRolesMenusQueryDTO queryDTO);
}
