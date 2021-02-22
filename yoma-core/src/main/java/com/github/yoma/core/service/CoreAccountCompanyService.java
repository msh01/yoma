package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreAccountCompanyDao;
import com.github.yoma.core.domain.CoreAccountCompany;
import com.github.yoma.core.dto.CoreAccountCompanyQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 集团账户关系 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreAccountCompanyService extends CrudService<CoreAccountCompanyDao, CoreAccountCompany> {

    @Override
    public CoreAccountCompany get(Long id) {
        return super.get(id);
    }

    public List<CoreAccountCompany> findList(CoreAccountCompanyQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreAccountCompany> findPage(CoreAccountCompanyQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreAccountCompanyQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreAccountCompany coreAccountCompany) {
        super.save(coreAccountCompany);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreAccountCompany coreAccountCompany) {
        return super.delete(coreAccountCompany);
    }

}
