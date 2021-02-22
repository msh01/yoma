package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreRoleDao;
import com.github.yoma.core.domain.CoreRole;
import com.github.yoma.core.dto.CoreRoleQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 角色信息 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreRoleService extends CrudService<CoreRoleDao, CoreRole> {

    @Override
    public CoreRole get(Long id) {
        return super.get(id);
    }

    public List<CoreRole> findList(CoreRoleQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreRole> findPage(CoreRoleQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreRoleQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreRole coreRole) {
        super.save(coreRole);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreRole coreRole) {
        return super.delete(coreRole);
    }

}
