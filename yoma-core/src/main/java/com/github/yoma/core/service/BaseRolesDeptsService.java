package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseRolesDeptsDao;
import com.github.yoma.core.domain.BaseRolesDepts;
import com.github.yoma.core.dto.BaseRolesDeptsQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 角色部门关联 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseRolesDeptsService extends CrudService<BaseRolesDeptsDao, BaseRolesDepts> {

    @Override
    public BaseRolesDepts get(Long id) {
        return super.get(id);
    }

    public List<BaseRolesDepts> findList(BaseRolesDeptsQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseRolesDepts> findPage(BaseRolesDeptsQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseRolesDeptsQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseRolesDepts baseRolesDepts) {
        super.save(baseRolesDepts);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseRolesDepts baseRolesDepts) {
        return super.delete(baseRolesDepts);
    }

}
