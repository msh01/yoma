package com.github.yoma.core.dao;

import com.github.yoma.core.domain.CoreAccount;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 账户信息DAO接口
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Repository
public interface CoreAccountDao extends CrudDao<CoreAccount> {

}
