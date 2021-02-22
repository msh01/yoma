package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseDictDetailDao;
import com.github.yoma.core.domain.BaseDictDetail;
import com.github.yoma.core.dto.BaseDictDetailQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 数据字典详情 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseDictDetailService extends CrudService<BaseDictDetailDao, BaseDictDetail> {

    @Override
    public BaseDictDetail get(Long id) {
        return super.get(id);
    }

    public List<BaseDictDetail> findList(BaseDictDetailQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseDictDetail> findPage(BaseDictDetailQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseDictDetailQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseDictDetail baseDictDetail) {
        super.save(baseDictDetail);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseDictDetail baseDictDetail) {
        return super.delete(baseDictDetail);
    }

}
