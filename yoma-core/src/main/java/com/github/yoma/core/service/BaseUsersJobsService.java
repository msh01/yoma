package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseUsersJobsDao;
import com.github.yoma.core.domain.BaseUsersJobs;
import com.github.yoma.core.dto.BaseUsersJobsQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 用户岗位关联 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseUsersJobsService extends CrudService<BaseUsersJobsDao, BaseUsersJobs> {

    @Override
    public BaseUsersJobs get(Long id) {
        return super.get(id);
    }

    public List<BaseUsersJobs> findList(BaseUsersJobsQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseUsersJobs> findPage(BaseUsersJobsQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseUsersJobsQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseUsersJobs baseUsersJobs) {
        super.save(baseUsersJobs);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseUsersJobs baseUsersJobs) {
        return super.delete(baseUsersJobs);
    }

}
