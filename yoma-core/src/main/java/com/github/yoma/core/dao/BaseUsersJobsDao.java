package com.github.yoma.core.dao;

import org.springframework.stereotype.Repository;

import com.github.yoma.core.domain.BaseUsersJobs;
import com.github.yoma.common.persistence.CrudDao;

/**
 * 用户岗位关联DAO接口
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Repository
public interface BaseUsersJobsDao extends CrudDao<BaseUsersJobs> {

}
