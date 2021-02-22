package com.github.yoma.core.service;

import java.util.ArrayList;
import java.util.List;

import com.github.yoma.core.domain.*;
import com.github.yoma.core.dto.*;
import com.github.yoma.common.exception.EntityExistException;
import com.github.yoma.common.exception.EntityNotFoundException;
import com.github.yoma.common.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseUserDao;
import com.github.yoma.core.dto.BaseUserQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 系统用户 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseUserService extends CrudService<BaseUserDao, BaseUser> {
    @Autowired
    BaseDeptService deptService;
    @Autowired
    BaseRoleService roleService;
    @Autowired
    BaseJobService jobService;
    @Autowired
    BaseUsersRolesService baseUsersRolesService;

    @Override
    public BaseUser get(Long id) {
        return super.get(id);
    }

    public BaseUser findByName(String userName) {
        BaseUserQueryDTO BaseUserQueryDTO = new BaseUserQueryDTO();
        BaseUser user = null;
        if (ValidationUtil.isEmail(userName)) {
        } else {
            BaseUserQueryDTO.setUsername(userName);
            List<BaseUser> list = this.findList(BaseUserQueryDTO);
            if (list.isEmpty()) {
                throw new EntityNotFoundException(BaseUser.class, "name", userName);
            } else {
                user = list.get(0);
            }
        }

        if (user == null) {
            throw new EntityNotFoundException(BaseUser.class, "name", userName);
        } else {
            return user;
        }
    }

    public List<BaseUser> findList(BaseUserQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseUser> findPage(BaseUserQueryDTO queryDTO) {
        PageInfo<BaseUser> page = super.findPage(queryDTO);
        page.getList().stream().forEach(x -> {
            Long deptId = x.getDeptId();
            BaseDept baseDept = deptService.get(deptId);
            x.setDept(baseDept);
            BaseRoleQueryDTO baseRoleQueryDTO = new BaseRoleQueryDTO();
            Long id = x.getId();
            baseRoleQueryDTO.setUserId(id);
            List<BaseRole> roles = roleService.findList(baseRoleQueryDTO);
            x.setRoles(roles);

            BaseJobQueryDTO baseJobQueryDTO = new BaseJobQueryDTO();
            baseJobQueryDTO.setUserId(id);
            List<BaseJob> jobs = jobService.findList(baseJobQueryDTO);
            x.setJobs(jobs);

        });
        return page;
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseUserQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseUser baseUser) {
        if (baseUser.getId() == null) {
            BaseUserQueryDTO baseUserQueryDTO = new BaseUserQueryDTO();
            String username = baseUser.getUsername();
            baseUserQueryDTO.setUsername(username);
            List<BaseUser> list1 = this.findList(baseUserQueryDTO);
            baseUserQueryDTO.setUsername(null);
            String phone = baseUser.getPhone();
            baseUserQueryDTO.setPhone(phone);
            List<BaseUser> list2 = this.findList(baseUserQueryDTO);
            if (!list1.isEmpty()) {
                throw new EntityExistException(BaseUser.class, "username", username);
            }
            // if (userRepository.findByEmail(resources.getEmail()) != null) {
            // throw new EntityExistException(User.class, "email", resources.getEmail());
            // }
            if (!list2.isEmpty()) {
                throw new EntityExistException(BaseUser.class, "phone", phone);
            }
            baseUser.setEnabled(true);
            BaseDept dept = baseUser.getDept();
            baseUser.setDeptId(dept.getId());
            super.save(baseUser);
            saveRolesRelation(baseUser);
        } else {
            baseUser.setEnabled(true);
            BaseDept dept = baseUser.getDept();
            baseUser.setDeptId(dept.getId());
            super.save(baseUser);
            BaseUsersRolesQueryDTO baseUsersRolesQueryDTO = new BaseUsersRolesQueryDTO();
            baseUsersRolesQueryDTO.setUserId(baseUser.getId());
            baseUsersRolesService.deleteByUserId(baseUsersRolesQueryDTO);
            saveRolesRelation(baseUser);
        }
    }

    private void saveRolesRelation(BaseUser baseUser) {
        List<BaseRole> roles = baseUser.getRoles();
        ArrayList<BaseUsersRoles> baseUsersRolesArrayList = new ArrayList<>();
        roles.forEach(x -> {
            BaseUsersRoles baseUsersRoles = new BaseUsersRoles();
            baseUsersRoles.setRoleId(x.getId());
            baseUsersRoles.setUserId(baseUser.getId());
            baseUsersRolesArrayList.add(baseUsersRoles);
        });
        this.baseUsersRolesService.batchInsert(baseUsersRolesArrayList);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseUser baseUser) {
        return super.delete(baseUser);
    }

}
