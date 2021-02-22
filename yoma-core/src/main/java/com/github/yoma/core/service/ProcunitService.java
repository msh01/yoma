package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.ProcunitDao;
import com.github.yoma.core.domain.Procunit;
import com.github.yoma.core.dto.ProcunitQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 流程环节配置 业务层
 * @author 马世豪
 * @version 2020-12-21
 */
@Service
@Transactional(readOnly = true)
public class ProcunitService extends CrudService<ProcunitDao, Procunit> {

	@Override
	public Procunit get(Long id) {
		return super.get(id);
	}

	public List<Procunit> findList(ProcunitQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<Procunit> findPage(ProcunitQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(ProcunitQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(Procunit procunit) {
		super.save(procunit);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(Procunit procunit) {
		return super.delete(procunit);
	}

}
