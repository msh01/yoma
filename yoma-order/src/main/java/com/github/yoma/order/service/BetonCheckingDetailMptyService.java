package com.github.yoma.order.service;

import java.util.List;

import com.github.yoma.order.dao.BetonCheckingDetailMptyDao;
import com.github.yoma.order.domain.BetonCheckingDetailMpty;
import com.github.yoma.order.dto.BetonCheckingDetailMptyQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 对账补充明细 业务层
 * @author 马世豪
 * @version 2020-12-02
 */
@Service
@Transactional(readOnly = true)
public class BetonCheckingDetailMptyService extends CrudService<BetonCheckingDetailMptyDao, BetonCheckingDetailMpty> {

	@Override
	public BetonCheckingDetailMpty get(Long id) {
		return super.get(id);
	}

	public List<BetonCheckingDetailMpty> findList(BetonCheckingDetailMptyQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<BetonCheckingDetailMpty> findPage(BetonCheckingDetailMptyQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(BetonCheckingDetailMptyQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(BetonCheckingDetailMpty betonCheckingDetailMpty) {
		super.save(betonCheckingDetailMpty);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(BetonCheckingDetailMpty betonCheckingDetailMpty) {
		return super.delete(betonCheckingDetailMpty);
	}

}
