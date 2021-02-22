package com.github.yoma.core.dao;

import com.github.yoma.core.domain.UserMoney;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 账户资金DAO接口
 * @author 马世豪
 * @version 2020-12-21
 */
@Repository
public interface UserMoneyDao extends CrudDao<UserMoney> {

}
