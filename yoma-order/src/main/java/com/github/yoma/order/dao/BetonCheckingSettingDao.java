package com.github.yoma.order.dao;

import com.github.yoma.order.domain.BetonCheckingSetting;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 对账周期DAO接口
 * @author 马世豪
 * @version 2020-11-30
 */
@Repository
public interface BetonCheckingSettingDao extends CrudDao<BetonCheckingSetting> {

}
