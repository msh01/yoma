   package com.github.yoma.logging.dao;
// package com.github.yoma.iot.dao;

   import org.springframework.stereotype.Repository;

   import com.github.yoma.logging.domain.CoreSysLog;
   import com.github.yoma.common.persistence.CrudDao;

/**
 * 系统日志DAO接口
 * @author 马世豪
 * @version 2020-11-02
 */
@Repository
public interface CoreSysLogDao extends CrudDao<CoreSysLog> {

}
