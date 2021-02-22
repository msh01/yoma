package com.github.yoma.core.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.CoreAccountRole;
import com.github.yoma.core.dto.CoreAccountRoleQueryDTO;
import com.github.yoma.core.service.CoreAccountRoleService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 平台账号-角色关系RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreAccountRole")
@Api(tags = "平台账号-角色关系")
public class CoreAccountRoleController extends BaseController {

    @Autowired
    private CoreAccountRoleService coreAccountRoleService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public CommonResponse<CoreAccountRole> list(@RequestBody CoreAccountRoleQueryDTO queryDTO) {
        PageInfo<CoreAccountRole> pageInfo = coreAccountRoleService.findPage(queryDTO);
        PageResponse<CoreAccountRole> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public CommonResponse<CoreAccountRole> save(@RequestBody CoreAccountRole coreAccountRole) {
        coreAccountRoleService.save(coreAccountRole);
        CommonResponse<CoreAccountRole> success = ResponseUtil.detailSuccess(coreAccountRole);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreAccountRoleId}")
    @AnonymousAccess
    public CommonResponse<CoreAccountRole> detail(@PathVariable Long coreAccountRoleId) {
        CoreAccountRole coreAccountRole = new CoreAccountRole();
        coreAccountRole.setId(coreAccountRoleId);
        coreAccountRole = coreAccountRoleService.get(coreAccountRole);
        CommonResponse<CoreAccountRole> success = ResponseUtil.detailSuccess(coreAccountRole);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreAccountRoleId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long coreAccountRoleId) {
        CoreAccountRole coreAccountRole = new CoreAccountRole();
        coreAccountRole.setId(coreAccountRoleId);
        int count = coreAccountRoleService.delete(coreAccountRole);
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
        CoreAccountRoleQueryDTO queryDTO = new CoreAccountRoleQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreAccountRoleService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
