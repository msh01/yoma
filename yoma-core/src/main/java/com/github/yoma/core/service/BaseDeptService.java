package com.github.yoma.core.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseDeptDao;
import com.github.yoma.core.domain.BaseDept;
import com.github.yoma.core.dto.BaseDeptQueryDTO;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.common.utils.ValidationUtil;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 部门 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseDeptService extends CrudService<BaseDeptDao, BaseDept> {

    @Override
    public BaseDept get(Long id) {
        return super.get(id);
    }

    public List<BaseDept> findList(BaseDeptQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseDept> findPage(BaseDeptQueryDTO queryDTO) {
        PageInfo<BaseDept> page = super.findPage(queryDTO);
        List<BaseDept> list = page.getList();
        List<BaseDept> deduplication = this.deduplication(list);
        return page;
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseDeptQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseDept baseDept) {
        super.save(baseDept);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseDept baseDept) {
        return super.delete(baseDept);
    }

    public List<BaseDept> deduplication(List<BaseDept> list) {
        List<BaseDept> BaseDepts = new ArrayList<>();
        for (BaseDept BaseDept : list) {
            boolean flag = true;
            for (BaseDept dto : list) {
                if (dto.getId().equals(BaseDept.getPid())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                BaseDepts.add(BaseDept);
            }
        }
        return BaseDepts;
    }

    // @Cacheable(key = "'id:' + #p0")
    public BaseDept findById(Long id) {
        // BaseDept dept = deptRepository.findById(id).orElseGet(Dept::new);
        BaseDept dept = this.get(id);
        if (dept == null) {
            dept = new BaseDept();
        } else {
        }
        ValidationUtil.isNull(dept.getId(), "Dept", "id", id);
        return dept;
    }

    public List<BaseDept> getSuperior(BaseDept BaseDept, List<BaseDept> depts) {
        BaseDeptQueryDTO baseDeptQueryDTO = new BaseDeptQueryDTO();
        if (BaseDept.getPid() == null) {
            depts.addAll(dao.findList(baseDeptQueryDTO));
            return depts;
        }
        baseDeptQueryDTO.setPid(BaseDept.getPid());
        depts.addAll(dao.findList(baseDeptQueryDTO));
        return getSuperior(findById(BaseDept.getPid()), depts);
    }

    public Object buildTree(List<BaseDept> BaseDepts) {
        Set<BaseDept> trees = new LinkedHashSet<>();
        Set<BaseDept> depts = new LinkedHashSet<>();
        List<String> deptNames = BaseDepts.stream().map(BaseDept::getName).collect(Collectors.toList());
        boolean isChild;
        for (BaseDept BaseDept : BaseDepts) {
            isChild = false;
            if (BaseDept.getPid() == null) {
                trees.add(BaseDept);
            }
            for (BaseDept it : BaseDepts) {
                if (it.getPid() != null && BaseDept.getId().equals(it.getPid())) {
                    isChild = true;
                    if (BaseDept.getChildren() == null) {
                        BaseDept.setChildren(new ArrayList<>());
                    }
                    BaseDept.getChildren().add(it);
                }
            }
            if (isChild) {
                depts.add(BaseDept);
            } else if (BaseDept.getPid() != null && !deptNames.contains(findById(BaseDept.getPid()).getName())) {
                depts.add(BaseDept);
            }
        }

        if (CollectionUtil.isEmpty(trees)) {
            trees = depts;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("totalElements", BaseDepts.size());
        map.put("content", CollectionUtil.isEmpty(trees) ? BaseDepts : trees);
        return map;
    }
}
