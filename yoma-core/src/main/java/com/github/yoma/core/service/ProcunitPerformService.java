package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.ProcunitPerformDao;
import com.github.yoma.core.domain.ProcunitPerform;
import com.github.yoma.core.dto.ProcunitPerformQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 流程环节明细 业务层
 * @author 马世豪
 * @version 2020-12-21
 */
@Service
@Transactional(readOnly = true)
public class ProcunitPerformService extends CrudService<ProcunitPerformDao, ProcunitPerform> {

	@Override
	public ProcunitPerform get(Long id) {
		return super.get(id);
	}

	public List<ProcunitPerform> findList(ProcunitPerformQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<ProcunitPerform> findPage(ProcunitPerformQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(ProcunitPerformQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(ProcunitPerform procunitPerform) {
		super.save(procunitPerform);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(ProcunitPerform procunitPerform) {
		return super.delete(procunitPerform);
	}

}
