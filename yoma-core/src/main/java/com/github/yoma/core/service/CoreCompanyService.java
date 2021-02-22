package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreCompanyDao;
import com.github.yoma.core.domain.CoreCompany;
import com.github.yoma.core.dto.CoreCompanyQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 集团/公司 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreCompanyService extends CrudService<CoreCompanyDao, CoreCompany> {

    @Override
    public CoreCompany get(Long id) {
        return super.get(id);
    }

    public List<CoreCompany> findList(CoreCompanyQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreCompany> findPage(CoreCompanyQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreCompanyQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreCompany coreCompany) {
        super.save(coreCompany);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreCompany coreCompany) {
        return super.delete(coreCompany);
    }

}
