package com.github.yoma.core.dao;

import com.github.yoma.core.domain.CoreAccountCompany;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 集团账户关系DAO接口
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Repository
public interface CoreAccountCompanyDao extends CrudDao<CoreAccountCompany> {

}
