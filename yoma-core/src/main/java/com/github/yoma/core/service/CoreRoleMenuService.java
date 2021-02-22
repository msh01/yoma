package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreRoleMenuDao;
import com.github.yoma.core.domain.CoreRoleMenu;
import com.github.yoma.core.dto.CoreRoleMenuQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 角色菜单 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreRoleMenuService extends CrudService<CoreRoleMenuDao, CoreRoleMenu> {

    @Override
    public CoreRoleMenu get(Long id) {
        return super.get(id);
    }

    public List<CoreRoleMenu> findList(CoreRoleMenuQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreRoleMenu> findPage(CoreRoleMenuQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreRoleMenuQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreRoleMenu coreRoleMenu) {
        super.save(coreRoleMenu);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreRoleMenu coreRoleMenu) {
        return super.delete(coreRoleMenu);
    }

}
