package com.github.yoma.core.service;

import java.util.List;

import com.github.yoma.core.dao.CoreMenuDao;
import com.github.yoma.core.domain.CoreMenu;
import com.github.yoma.core.dto.CoreMenuQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
 * 菜单和操作按钮 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly = true)
public class CoreMenuService extends CrudService<CoreMenuDao, CoreMenu> {

    @Override
    public CoreMenu get(Long id) {
        return super.get(id);
    }

    public List<CoreMenu> findList(CoreMenuQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreMenu> findPage(CoreMenuQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreMenuQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreMenu coreMenu) {
        super.save(coreMenu);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreMenu coreMenu) {
        return super.delete(coreMenu);
    }

}
