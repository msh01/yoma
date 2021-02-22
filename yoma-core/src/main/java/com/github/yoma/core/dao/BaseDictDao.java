package com.github.yoma.core.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseDict;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 数据字典DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseDictDao extends CrudDao<BaseDict> {

}
