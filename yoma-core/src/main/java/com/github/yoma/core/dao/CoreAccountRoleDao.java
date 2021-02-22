package com.github.yoma.core.dao;

import com.github.yoma.core.domain.CoreAccountRole;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 平台账号-角色关系DAO接口
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Repository
public interface CoreAccountRoleDao extends CrudDao<CoreAccountRole> {

}
