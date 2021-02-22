package com.github.yoma.core.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseDept;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 部门DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseDeptDao extends CrudDao<BaseDept> {

}
