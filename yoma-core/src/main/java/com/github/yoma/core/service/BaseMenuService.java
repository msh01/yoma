package com.github.yoma.core.service;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.yoma.core.domain.BaseRole;
import com.github.yoma.core.dto.BaseRoleQueryDTO;
import com.github.yoma.core.vo.MenuMetaVo;
import com.github.yoma.core.vo.MenuVo;
import com.github.yoma.common.exception.BadRequestException;
import com.github.yoma.common.exception.EntityExistException;
import com.github.yoma.common.utils.StringUtils;
import com.github.yoma.common.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.github.yoma.core.dao.BaseMenuDao;
import com.github.yoma.core.domain.BaseMenu;
import com.github.yoma.core.dto.BaseMenuQueryDTO;
import com.github.yoma.common.persistence.CrudService;

/**
 * 系统菜单 业务层
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Service
@Transactional(readOnly = true)
public class BaseMenuService extends CrudService<BaseMenuDao, BaseMenu> {
    @Autowired
    BaseRoleService baseRoleService;
    @Autowired
    BaseMenuService baseMenuService;

    public List<BaseMenu> getMenus(Long pid) {
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        if (pid != null && !pid.equals(0L)) {
            baseMenuQueryDTO.setPid(pid);
        } else {
            baseMenuQueryDTO.setPidIsNull(1);
        }
        List<BaseMenu> menus = baseMenuService.findList(baseMenuQueryDTO);
        return menus;
    }

    public List<BaseMenu> findByUser(Long currentUserId) {
        BaseRoleQueryDTO baseRoleQueryDTO = new BaseRoleQueryDTO();
        baseRoleQueryDTO.setUserId(currentUserId);
        List<BaseRole> roles = baseRoleService.findList(baseRoleQueryDTO);

        // List<RoleSmallDto> roles = roleService.findByUsersId(currentUserId);
        Set<Long> roleIds = roles.stream().map(BaseRole::getId).collect(Collectors.toSet());
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        baseMenuQueryDTO.setTypeNot(2L);
        baseMenuQueryDTO.setRoleIds(roleIds);
        List<BaseMenu> menus = baseMenuService.findList(baseMenuQueryDTO);
        // LinkedHashSet<BaseMenu> menus = menuRepository.findByRoleIdsAndTypeNot(roleIds, 2);
        return menus;
    }

    public List<MenuVo> buildMenus(List<BaseMenu> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
            if (menuDTO != null) {
                List<BaseMenu> menuDtoList = menuDTO.getChildren();
                MenuVo menuVo = new MenuVo();
                menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName()
                    : menuDTO.getTitle());
                // 一级目录需要加斜杠，不然会报警告
                menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() : menuDTO.getPath());
                menuVo.setHidden(menuDTO.getHidden());
                // 如果不是外链
                if (!menuDTO.getIFrame()) {
                    if (menuDTO.getPid() == null) {
                        menuVo
                            .setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                    } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                        menuVo.setComponent(menuDTO.getComponent());
                    }
                }
                menuVo.setMeta(new MenuMetaVo(menuDTO.getTitle(), menuDTO.getIcon(), !menuDTO.getCache()));
                if (menuDtoList != null && menuDtoList.size() != 0) {
                    menuVo.setAlwaysShow(true);
                    menuVo.setRedirect("noredirect");
                    menuVo.setChildren(buildMenus(menuDtoList));
                    // 处理是一级菜单并且没有子菜单的情况
                } else if (menuDTO.getPid() == null) {
                    MenuVo menuVo1 = new MenuVo();
                    menuVo1.setMeta(menuVo.getMeta());
                    // 非外链
                    if (!menuDTO.getIFrame()) {
                        menuVo1.setPath("index");
                        menuVo1.setName(menuVo.getName());
                        menuVo1.setComponent(menuVo.getComponent());
                    } else {
                        menuVo1.setPath(menuDTO.getPath());
                    }
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<MenuVo> list1 = new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                }
                list.add(menuVo);
            }
        });
        return list;
    }

    public List<BaseMenu> getSuperior(BaseMenu menuDto, List<BaseMenu> menus) {
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        if (menuDto.getPid() == null) {
            baseMenuQueryDTO.setPidIsNull(1);
            List<BaseMenu> list = dao.findList(baseMenuQueryDTO);
            menus.addAll(list);
            return menus;
        }
        baseMenuQueryDTO.setPid(menuDto.getPid());
        menus.addAll(dao.findList(baseMenuQueryDTO));
        return getSuperior(findById(menuDto.getPid()), menus);
    }

    public BaseMenu findById(long id) {
        BaseMenu menu = this.get(id);
        if (menu == null) {
            menu = new BaseMenu();
        }
        // BaseMenu menu = menuRepository.findById(id).orElseGet(BaseMenu::new);
        ValidationUtil.isNull(menu.getId(), "BaseMenu", "id", id);
        return menu;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(BaseMenu resources) {
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        baseMenuQueryDTO.setTitle(resources.getTitle());
        List<BaseMenu> list = this.findList(baseMenuQueryDTO);
        if (!list.isEmpty()) {
            throw new EntityExistException(BaseMenu.class, "title", resources.getTitle());
        }
        String componentName = resources.getComponentName();
        if (StringUtils.isNotBlank(componentName)) {
            baseMenuQueryDTO.setName(componentName);
            baseMenuQueryDTO.setTitle(null);
            list = this.findList(baseMenuQueryDTO);
            if (!list.isEmpty()) {
                throw new EntityExistException(BaseMenu.class, "componentName", componentName);
            }
        }
        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }
        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http)
                || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        resources.setName(componentName);
        dao.insert(resources);
        // 计算子节点数目
        resources.setSubCount(0);
        // 更新父节点菜单数目
        updateSubCnt(resources.getPid());
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(BaseMenu resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        BaseMenu menu = this.findById(resources.getId());
        ValidationUtil.isNull(menu.getId(), "Permission", "id", resources.getId());

        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http)
                || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
        baseMenuQueryDTO.setTitle(resources.getTitle());
        List<BaseMenu> list = this.findList(baseMenuQueryDTO);
        // if (!list.isEmpty()) {
        // throw new EntityExistException(BaseMenu.class, "title", resources.getTitle());
        // }

        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }

        // 记录的父节点ID
        Long oldPid = menu.getPid();
        Long newPid = resources.getPid();

        String componentName = resources.getComponentName();
        if (StringUtils.isNotBlank(componentName)) {
            baseMenuQueryDTO.setName(componentName);
            baseMenuQueryDTO.setTitle(null);
            list = this.findList(baseMenuQueryDTO);
            if (!list.isEmpty()) {
                throw new EntityExistException(BaseMenu.class, "componentName", componentName);
            }
        }
        menu.setTitle(resources.getTitle());
        menu.setComponent(resources.getComponent());
        menu.setPath(resources.getPath());
        menu.setIcon(resources.getIcon());
        menu.setIFrame(resources.getIFrame());
        menu.setPid(resources.getPid());
        menu.setMenuSort(resources.getMenuSort());
        menu.setCache(resources.getCache());
        menu.setHidden(resources.getHidden());
        menu.setComponentName(resources.getComponentName());
        menu.setPermission(resources.getPermission());
        menu.setType(resources.getType());
        dao.update(menu);
        // 计算父级菜单节点数目
        updateSubCnt(oldPid);
        updateSubCnt(newPid);
        // // 清理缓存
        // delCaches(resources.getId());
    }

    public Set<BaseMenu> getChildMenus(List<BaseMenu> menuList, Set<BaseMenu> menuSet) {
        for (BaseMenu menu : menuList) {
            menuSet.add(menu);
            BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
            baseMenuQueryDTO.setPid(menu.getId());
            List<BaseMenu> menus = dao.findList(baseMenuQueryDTO);
            // List<BaseMenu> menus = menuRepository.findByPid(menu.getId());
            if (menus != null && menus.size() != 0) {
                getChildMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    private void updateSubCnt(Long menuId) {
        if (menuId != null) {
            BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
            baseMenuQueryDTO.setPid(menuId);
            List<BaseMenu> list = dao.findList(baseMenuQueryDTO);
            int count = list.size();
            BaseMenu baseMenu = new BaseMenu();
            baseMenu.setId(menuId);
            baseMenu.setSubCount(count);
            dao.update(baseMenu);
        }
    }

    public List<BaseMenu> buildTree(List<BaseMenu> menuDtos) {
        List<BaseMenu> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (BaseMenu menuDTO : menuDtos) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (BaseMenu it : menuDtos) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public BaseMenu get(Long id) {
        return super.get(id);
    }

    public List<BaseMenu> findList(BaseMenuQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<BaseMenu> findPage(BaseMenuQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(BaseMenuQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    @Transactional(readOnly = false)
    @Override
    public void save(BaseMenu baseMenu) {
        super.save(baseMenu);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(BaseMenu baseMenu) {
        return super.delete(baseMenu);
    }

}
