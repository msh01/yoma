package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseUsersRolesDao;
import com.github.yoma.core.domain.BaseUsersRoles;
import com.github.yoma.core.dto.BaseUsersRolesQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 用户角色关联 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseUsersRolesService extends CrudService<BaseUsersRolesDao, BaseUsersRoles> {

    @Override
    public BaseUsersRoles get(Long id) {
        return super.get(id);
    }

    public List<BaseUsersRoles> findList(BaseUsersRolesQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseUsersRoles> findPage(BaseUsersRolesQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseUsersRolesQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    public int deleteByUserId(BaseUsersRolesQueryDTO queryDTO) {
        int count = this.dao.deleteByUserId(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseUsersRoles baseUsersRoles) {
        super.save(baseUsersRoles);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseUsersRoles baseUsersRoles) {
        return super.delete(baseUsersRoles);
    }

}
