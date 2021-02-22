package com.github.yoma.logging.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.logging.dao.CoreSysLogDao;
import com.github.yoma.logging.domain.CoreSysLog;
import com.github.yoma.logging.query.CoreSysLogQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 系统日志 业务层
 * @author 马世豪
 * @version 2020-11-02
 */
@Service
@Transactional(readOnly = true)
public class CoreSysLogService extends CrudService<CoreSysLogDao, CoreSysLog> {

	@Override
	public CoreSysLog get(Long id) {
		return super.get(id);
	}

	public List<CoreSysLog> findList(CoreSysLogQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<CoreSysLog> findPage(CoreSysLogQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(CoreSysLogQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(CoreSysLog coreSysLog) {
		super.save(coreSysLog);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(CoreSysLog coreSysLog) {
		return super.delete(coreSysLog);
	}
	
}
