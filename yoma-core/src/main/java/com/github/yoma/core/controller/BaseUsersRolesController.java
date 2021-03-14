package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.BaseUsersRoles;
import com.github.yoma.core.dto.BaseUsersRolesQueryDTO;
import com.github.yoma.core.service.BaseUsersRolesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户角色关联RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseUsersRoles")
@Api(tags = "用户角色关联")
public class BaseUsersRolesController extends BaseController {

    @Autowired
    private BaseUsersRolesService baseUsersRolesService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<BaseUsersRoles> list(@RequestBody BaseUsersRolesQueryDTO queryDTO) {
        PageInfo<BaseUsersRoles> pageInfo = baseUsersRolesService.findPage(queryDTO);
        PageResponse<BaseUsersRoles> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BaseUsersRoles> save(@RequestBody BaseUsersRoles baseUsersRoles) {
        baseUsersRolesService.save(baseUsersRoles);
        DetailResponse<BaseUsersRoles> success = ResponseUtil.detailSuccess(baseUsersRoles);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseUsersRolesId}")
    public DetailResponse<BaseUsersRoles> detail(@PathVariable Long baseUsersRolesId) {
        BaseUsersRoles baseUsersRoles = new BaseUsersRoles();
        baseUsersRoles.setId(baseUsersRolesId);
        baseUsersRoles = baseUsersRolesService.get(baseUsersRoles);
        DetailResponse<BaseUsersRoles> success = ResponseUtil.detailSuccess(baseUsersRoles);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseUsersRolesId}")
    public CommonResponse delete(@PathVariable Long baseUsersRolesId) {
        BaseUsersRoles baseUsersRoles = new BaseUsersRoles();
        baseUsersRoles.setId(baseUsersRolesId);
        int count = baseUsersRolesService.delete(baseUsersRoles);
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
        BaseUsersRolesQueryDTO queryDTO = new BaseUsersRolesQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseUsersRolesService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
