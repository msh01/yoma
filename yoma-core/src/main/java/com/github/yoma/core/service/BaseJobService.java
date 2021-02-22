package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseJobDao;
import com.github.yoma.core.domain.BaseJob;
import com.github.yoma.core.dto.BaseJobQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 岗位 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseJobService extends CrudService<BaseJobDao, BaseJob> {

    @Override
    public BaseJob get(Long id) {
        return super.get(id);
    }

    public List<BaseJob> findList(BaseJobQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseJob> findPage(BaseJobQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseJobQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseJob baseJob) {
        super.save(baseJob);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseJob baseJob) {
        return super.delete(baseJob);
    }

}
