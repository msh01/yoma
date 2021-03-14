package com.github.yoma.core.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.common.utils.SecurityUtils;
import com.github.yoma.core.domain.BaseMenu;
import com.github.yoma.core.dto.BaseMenuQueryDTO;
import com.github.yoma.core.service.BaseMenuService;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统菜单RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseMenu")
@Api(tags = "系统菜单")
public class BaseMenuController extends BaseController {

    @Autowired
    private BaseMenuService baseMenuService;

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy")
    public ResponseEntity<Object> query(@RequestParam Long pid) {
        return new ResponseEntity<>(baseMenuService.getMenus(pid), HttpStatus.OK);
    }

    // @ApiOperation("返回全部的菜单")
    // @GetMapping(value = "/lazy/{type}")
    // // public ResponseEntity<Object> querytest(@PathVariable Integer type) {
    //     return new ResponseEntity<>(baseMenuService.getMenus(pid), HttpStatus.OK);
    // }

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    public ResponseEntity<Object> buildMenus() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        List<BaseMenu> menuDtoList = baseMenuService.findByUser(currentUserId);
        List<BaseMenu> menuDtos = baseMenuService.buildTree(menuDtoList);
        return new ResponseEntity<>(baseMenuService.buildMenus(menuDtos), HttpStatus.OK);
    }

    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    public ResponseEntity<Object> getSuperior(@RequestBody List<Long> ids) {
        Set<BaseMenu> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (Long id : ids) {
                BaseMenu menuDto = baseMenuService.findById(id);
                menuDtos.addAll(baseMenuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(baseMenuService.buildTree(new ArrayList<>(menuDtos)), HttpStatus.OK);
        }
        return new ResponseEntity<>(baseMenuService.getMenus(null), HttpStatus.OK);
    }

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<BaseMenu> list(BaseMenuQueryDTO queryDTO) {
        if (queryDTO.getPid() == null) {
            queryDTO.setPidIsNull(1);
        }
        PageInfo<BaseMenu> pageInfo = baseMenuService.findPage(queryDTO);
        PageResponse<BaseMenu> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    @ApiOperation("根据菜单ID返回所有子节点ID，包含自身ID")
    @GetMapping(value = "/child")
    public ResponseEntity<Object> child(@RequestParam Long id) {
        Set<BaseMenu> menuSet = new HashSet<>();
        List<BaseMenu> menuList = this.baseMenuService.getMenus(id);
        menuSet.add(baseMenuService.findById(id));
        menuSet = baseMenuService.getChildMenus(menuList, menuSet);
        Set<Long> ids = menuSet.stream().map(BaseMenu::getId).collect(Collectors.toSet());
        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody BaseMenu baseMenu) {
        baseMenuService.create(baseMenu);
        DetailResponse<BaseMenu> success = ResponseUtil.detailSuccess(baseMenu);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = " 保存或修改")
    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody BaseMenu baseMenu) {
        baseMenuService.update(baseMenu);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseMenuId}")
    public DetailResponse<BaseMenu> detail(@PathVariable Long baseMenuId) {
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.setId(baseMenuId);
        baseMenu = baseMenuService.get(baseMenu);
        DetailResponse<BaseMenu> success = ResponseUtil.detailSuccess(baseMenu);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseMenuId}")
    public CommonResponse delete(@PathVariable Long baseMenuId) {
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.setId(baseMenuId);
        int count = baseMenuService.delete(baseMenu);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

    /**
     * 删除操作-批量
     */
    @ApiOperation("批量删除")
    @PostMapping("/batch/delete")
    public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
        // 获取当前操作人信息
        BaseMenuQueryDTO queryDTO = new BaseMenuQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseMenuService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
