package com.github.yoma.core.controller;

import cn.hutool.core.lang.Dict;
import com.github.yoma.core.domain.BaseMenu;
import com.github.yoma.core.dto.BaseMenuQueryDTO;
import com.github.yoma.core.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseRole;
import com.github.yoma.core.dto.BaseRoleQueryDTO;
import com.github.yoma.core.service.BaseRoleService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseRole")
@Api(tags = "角色")
public class BaseRoleController extends BaseController {

    @Autowired
    private BaseRoleService baseRoleService;
    @Autowired
    private BaseMenuService baseMenuService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BaseRole> list(BaseRoleQueryDTO queryDTO) {
        PageInfo<BaseRole> pageInfo = baseRoleService.findPage(queryDTO);
        pageInfo.getList().forEach(x -> {
            BaseMenuQueryDTO baseMenuQueryDTO = new BaseMenuQueryDTO();
            baseMenuQueryDTO.setRoleId(x.getId());
            List<BaseMenu> menus = baseMenuService.findList(baseMenuQueryDTO);
            x.setMenus(menus);
        });
        PageResponse<BaseRole> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    @ApiOperation("获取用户级别")
    @AnonymousAccess
    @GetMapping(value = "/level")
    public ResponseEntity<Object> getLevel() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        return new ResponseEntity<>(Dict.create().set("level", list), HttpStatus.OK);
    }

    @ApiOperation("修改角色菜单")
    @PostMapping(value = "/updateMenu")
    @AnonymousAccess
    public ResponseEntity<Object> updateMenu(@RequestBody BaseRole resources) {
        // RoleDto role = baseRoleService.findById(resources.getId());
        // getLevels(role.getLevel());
        baseRoleService.updateMenu(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseRole> save(@RequestBody BaseRole baseRole) {
        baseRoleService.save(baseRole);
        DetailResponse<BaseRole> success = ResponseUtil.detailSuccess(baseRole);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseRoleId}")
    @AnonymousAccess
    public DetailResponse<BaseRole> detail(@PathVariable Long baseRoleId) {
        BaseRole baseRole = new BaseRole();
        baseRole.setId(baseRoleId);
        baseRole = baseRoleService.get(baseRole);
        DetailResponse<BaseRole> success = ResponseUtil.detailSuccess(baseRole);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseRoleId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseRoleId) {
        BaseRole baseRole = new BaseRole();
        baseRole.setId(baseRoleId);
        int count = baseRoleService.delete(baseRole);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

    /**
     * 删除操作-批量
     */
    @ApiOperation("批量删除")
    @PostMapping("/batch/delete")
    @AnonymousAccess
    public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
        // 获取当前操作人信息
        BaseRoleQueryDTO queryDTO = new BaseRoleQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseRoleService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
