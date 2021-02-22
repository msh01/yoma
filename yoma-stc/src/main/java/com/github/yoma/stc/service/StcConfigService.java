package com.github.yoma.stc.service;

import java.util.List;

import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.stc.dao.StcConfigDao;
import com.github.yoma.stc.domain.StcConfig;
import com.github.yoma.stc.dto.StcConfigQueryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 组态 业务层
 * @author 马世豪
 * @version 2020-03-30
 */
@Service
@Transactional(readOnly = true)
public class StcConfigService extends CrudService<StcConfigDao, StcConfig> {

	@Override
	public StcConfig get(Long id) {
		return super.get(id);
	}

	public List<StcConfig> findList(StcConfigQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<StcConfig> findPage(StcConfigQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(StcConfigQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(StcConfig stcConfig) {
		super.save(stcConfig);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(StcConfig stcConfig) {
		return super.delete(stcConfig);
	}

}
