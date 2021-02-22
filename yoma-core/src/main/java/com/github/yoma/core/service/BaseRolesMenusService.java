package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseRolesMenusDao;
import com.github.yoma.core.domain.BaseRolesMenus;
import com.github.yoma.core.dto.BaseRolesMenusQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 角色菜单关联 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseRolesMenusService extends CrudService<BaseRolesMenusDao, BaseRolesMenus> {

    @Override
    public BaseRolesMenus get(Long id) {
        return super.get(id);
    }

    public List<BaseRolesMenus> findList(BaseRolesMenusQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseRolesMenus> findPage(BaseRolesMenusQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseRolesMenusQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseRolesMenus baseRolesMenus) {
        super.save(baseRolesMenus);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseRolesMenus baseRolesMenus) {
        return super.delete(baseRolesMenus);
    }

    @Transactional(readOnly = false)
    public int deleteByRoleId(BaseRolesMenusQueryDTO queryDTO) {
        return dao.deleteByRoleId(queryDTO);
    }

}
