package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreAccountRoleDao;
import com.github.yoma.core.domain.CoreAccountRole;
import com.github.yoma.core.dto.CoreAccountRoleQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 平台账号-角色关系 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreAccountRoleService extends CrudService<CoreAccountRoleDao, CoreAccountRole> {

    @Override
    public CoreAccountRole get(Long id) {
        return super.get(id);
    }

    public List<CoreAccountRole> findList(CoreAccountRoleQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreAccountRole> findPage(CoreAccountRoleQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreAccountRoleQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreAccountRole coreAccountRole) {
        super.save(coreAccountRole);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreAccountRole coreAccountRole) {
        return super.delete(coreAccountRole);
    }

}
