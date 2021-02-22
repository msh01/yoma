package com.github.yoma.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.yoma.core.domain.BaseMenu;
import com.github.yoma.core.domain.BaseRolesMenus;
import com.github.yoma.core.domain.BaseUser;
import com.github.yoma.core.dto.BaseMenuQueryDTO;
import com.github.yoma.core.dto.BaseRolesMenusQueryDTO;
import com.github.yoma.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseRoleDao;
import com.github.yoma.core.domain.BaseRole;
import com.github.yoma.core.dto.BaseRoleQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 角色 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseRoleService extends CrudService<BaseRoleDao, BaseRole> {

    @Autowired
    BaseMenuService baseMenuService;
    @Autowired
    BaseRolesMenusService baseRolesMenusService;

    @Override
    public BaseRole get(Long id) {
        return super.get(id);
    }
    @Transactional(readOnly = false)
    public void updateMenu(BaseRole resources) {
        // List<User> users = userRepository.findByRoleId(role.getId());
        // delCaches(resources.getId(), users);

        List<BaseMenu> menus = resources.getMenus();
        Long roleId = resources.getId();
        ArrayList<BaseRolesMenus> baseRolesMenusList = new ArrayList<>();
        menus.forEach(x -> {
            BaseRolesMenus baseRolesMenus = new BaseRolesMenus();
            baseRolesMenus.setMenuId(x.getId());
            baseRolesMenus.setRoleId(roleId);
            baseRolesMenusList.add(baseRolesMenus);
        });
        BaseRolesMenusQueryDTO baseRolesMenusQueryDTO = new BaseRolesMenusQueryDTO();
        baseRolesMenusQueryDTO.setRoleId(roleId);
        baseRolesMenusService.deleteByRoleId(baseRolesMenusQueryDTO);
        baseRolesMenusService.batchInsert(baseRolesMenusList);
    }

    public List<BaseRole> findList(BaseRoleQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseRole> findPage(BaseRoleQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseRoleQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    // @Cacheable(key = "'auth:' + #p0.id")
    public List<GrantedAuthority> mapToGrantedAuthorities(BaseUser user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        BaseRoleQueryDTO baseRoleQueryDTO = new BaseRoleQueryDTO();
        baseRoleQueryDTO.setUserId(user.getId());

        List<BaseRole> roles = this.findList(baseRoleQueryDTO);
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        roles.forEach(x -> {
            baseMenuQueryDTO.setRoleId(x.getId());
            List<BaseMenu> menus = baseMenuService.findList(baseMenuQueryDTO);
            x.setMenus(menus);
        });

        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
            .filter(menu -> StringUtils.isNotBlank(menu.getPermission())).map(BaseMenu::getPermission)
            .collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseRole baseRole) {
        super.save(baseRole);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseRole baseRole) {
        return super.delete(baseRole);
    }

}
