package com.github.yoma.stc.dao;

import com.github.yoma.common.persistence.CrudDao;
import com.github.yoma.stc.domain.StcConfig;
import org.springframework.stereotype.Repository;

/**
 * 组态DAO接口
 * @author 马世豪
 * @version 2020-03-30
 */
@Repository
public interface StcConfigDao extends CrudDao<StcConfig> {

}
