/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.github.yoma.common.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * Service基类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Slf4j
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

    public static final String ERROR = "error";
    public static final String RESULT = "result";

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(Long id) {
        return dao.get(id);
    }

    /**
     * 查询列表数据
     *
     * @param query
     * @return
     */
    public List<T> findList(BaseQueryDTO query) {
        return dao.findList(query);
    }

    /**
     * 查询分页数据
     *
     * @param query
     *            查询条件
     * @return
     */
    public PageInfo<T> findPage(BaseQueryDTO query) {
        if (query.getPageNo() == null || query.getPageNo() == -1) {
            // 不启用分页
            log.info("当前不启用分页");
        } else {
            log.info("当前启用分页");
            PageHelper.startPage(query.getPageNo(), query.getPageSize());
        }
        List<T> list = dao.findList(query);
        PageInfo<T> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        // if (entity.getIsNewRecord()){
        if (entity.getId() == null || entity.getId() == 0) { // modified by msh 20190929 修改判定当前是插入还是修改的逻辑
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
    }

    /**
     * 批量插入数据
     *
     * @param entityList
     */
    @Transactional(readOnly = false)
    public void batchInsert(List<T> entityList) {
        if (entityList != null && !entityList.isEmpty()) {
            entityList.forEach(entity -> {
                entity.preInsert();
            });
            dao.batchInsert(entityList);
        } else {
            logger.info("list为空，不执行批量插入");
        }
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int delete(T entity) {
        return dao.delete(entity);
    }

    /**
     * 批量删除数据
     *
     * @param query
     * @return
     */
    public int batchDelete(BaseQueryDTO query) {
        int count = dao.batchDelete(query);
        return count;
    }

}
