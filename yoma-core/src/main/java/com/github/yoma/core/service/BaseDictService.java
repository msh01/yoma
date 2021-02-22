package com.github.yoma.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseDictDao;
import com.github.yoma.core.domain.BaseDict;
import com.github.yoma.core.dto.BaseDictQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 数据字典 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseDictService extends CrudService<BaseDictDao, BaseDict> {

    @Override
    public BaseDict get(Long id) {
        return super.get(id);
    }

    public List<BaseDict> findList(BaseDictQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseDict> findPage(BaseDictQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseDictQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseDict baseDict) {
        super.save(baseDict);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseDict baseDict) {
        return super.delete(baseDict);
    }

}
