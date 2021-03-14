package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreRoleMenu;
import com.github.yoma.core.dto.CoreRoleMenuQueryDTO;
import com.github.yoma.core.service.CoreRoleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色菜单RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreRoleMenu")
@Api(tags = "角色菜单")
public class CoreRoleMenuController extends BaseController {

    @Autowired
    private CoreRoleMenuService coreRoleMenuService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreRoleMenu> list(@RequestBody CoreRoleMenuQueryDTO queryDTO) {
        PageInfo<CoreRoleMenu> pageInfo = coreRoleMenuService.findPage(queryDTO);
        PageResponse<CoreRoleMenu> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreRoleMenu> save(@RequestBody CoreRoleMenu coreRoleMenu) {
        coreRoleMenuService.save(coreRoleMenu);
        CommonResponse<CoreRoleMenu> success = ResponseUtil.detailSuccess(coreRoleMenu);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreRoleMenuId}")
    public CommonResponse<CoreRoleMenu> detail(@PathVariable Long coreRoleMenuId) {
        CoreRoleMenu coreRoleMenu = new CoreRoleMenu();
        coreRoleMenu.setId(coreRoleMenuId);
        coreRoleMenu = coreRoleMenuService.get(coreRoleMenu);
        CommonResponse<CoreRoleMenu> success = ResponseUtil.detailSuccess(coreRoleMenu);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreRoleMenuId}")
    public CommonResponse delete(@PathVariable Long coreRoleMenuId) {
        CoreRoleMenu coreRoleMenu = new CoreRoleMenu();
        coreRoleMenu.setId(coreRoleMenuId);
        int count = coreRoleMenuService.delete(coreRoleMenu);
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
        CoreRoleMenuQueryDTO queryDTO = new CoreRoleMenuQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreRoleMenuService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
