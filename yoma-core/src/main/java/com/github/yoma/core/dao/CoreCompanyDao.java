package com.github.yoma.core.dao;

import com.github.yoma.core.domain.CoreCompany;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 集团/公司DAO接口
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Repository
public interface CoreCompanyDao extends CrudDao<CoreCompany> {

}
