package com.github.yoma.order.service;

import java.util.List;

import com.github.yoma.order.dao.BetonCheckingSettingDao;
import com.github.yoma.order.domain.BetonCheckingSetting;
import com.github.yoma.order.dto.BetonCheckingSettingQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 对账周期 业务层
 * @author 马世豪
 * @version 2020-11-30
 */
@Service
@Transactional(readOnly = true)
public class BetonCheckingSettingService extends CrudService<BetonCheckingSettingDao, BetonCheckingSetting> {

	@Override
	public BetonCheckingSetting get(Long id) {
		return super.get(id);
	}

	public List<BetonCheckingSetting> findList(BetonCheckingSettingQueryDTO queryDTO) {
		return super.findList(queryDTO);
	}

	public PageInfo<BetonCheckingSetting> findPage(BetonCheckingSettingQueryDTO queryDTO) {
		return super.findPage(queryDTO);
	}

	@Transactional(readOnly = false)
    public int batchDelete(BetonCheckingSettingQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }


	@Transactional(readOnly = false)
	@Override
	public void save(BetonCheckingSetting betonCheckingSetting) {
		betonCheckingSetting.setCreateId(1L);
		// betonCheckingSetting.up(1L);
		super.save(betonCheckingSetting);
	}


	@Transactional(readOnly = false)
	@Override
	public int delete(BetonCheckingSetting betonCheckingSetting) {
		return super.delete(betonCheckingSetting);
	}

}
