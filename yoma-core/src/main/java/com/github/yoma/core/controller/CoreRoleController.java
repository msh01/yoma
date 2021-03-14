package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreRole;
import com.github.yoma.core.dto.CoreRoleQueryDTO;
import com.github.yoma.core.service.CoreRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色信息RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreRole")
@Api(tags = "角色信息")
public class CoreRoleController extends BaseController {

    @Autowired
    private CoreRoleService coreRoleService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreRole> list(@RequestBody CoreRoleQueryDTO queryDTO) {
        PageInfo<CoreRole> pageInfo = coreRoleService.findPage(queryDTO);
        PageResponse<CoreRole> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreRole> save(@RequestBody CoreRole coreRole) {
        coreRoleService.save(coreRole);
        CommonResponse<CoreRole> success = ResponseUtil.detailSuccess(coreRole);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreRoleId}")
    public CommonResponse<CoreRole> detail(@PathVariable Long coreRoleId) {
        CoreRole coreRole = new CoreRole();
        coreRole.setId(coreRoleId);
        coreRole = coreRoleService.get(coreRole);
        CommonResponse<CoreRole> success = ResponseUtil.detailSuccess(coreRole);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreRoleId}")
    public CommonResponse delete(@PathVariable Long coreRoleId) {
        CoreRole coreRole = new CoreRole();
        coreRole.setId(coreRoleId);
        int count = coreRoleService.delete(coreRole);
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
        CoreRoleQueryDTO queryDTO = new CoreRoleQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreRoleService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
