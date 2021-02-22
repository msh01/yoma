package com.github.yoma.core.dao;

import com.github.yoma.core.domain.CoreMenu;
import com.github.yoma.common.persistence.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * 菜单和操作按钮DAO接口
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Repository
public interface CoreMenuDao extends CrudDao<CoreMenu> {

}
