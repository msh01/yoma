package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreAccountProjectDao;
import com.github.yoma.core.domain.CoreAccountProject;
import com.github.yoma.core.dto.CoreAccountProjectQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 项目角色关系 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreAccountProjectService extends CrudService<CoreAccountProjectDao, CoreAccountProject> {

    @Override
    public CoreAccountProject get(Long id) {
        return super.get(id);
    }

    public List<CoreAccountProject> findList(CoreAccountProjectQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreAccountProject> findPage(CoreAccountProjectQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreAccountProjectQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreAccountProject coreAccountProject) {
        super.save(coreAccountProject);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreAccountProject coreAccountProject) {
        return super.delete(coreAccountProject);
    }

}
