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
import com.github.yoma.core.domain.BaseRolesDepts;
import com.github.yoma.core.dto.BaseRolesDeptsQueryDTO;
import com.github.yoma.core.service.BaseRolesDeptsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色部门关联RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseRolesDepts")
@Api(tags = "角色部门关联")
public class BaseRolesDeptsController extends BaseController {

    @Autowired
    private BaseRolesDeptsService baseRolesDeptsService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<BaseRolesDepts> list(@RequestBody BaseRolesDeptsQueryDTO queryDTO) {
        PageInfo<BaseRolesDepts> pageInfo = baseRolesDeptsService.findPage(queryDTO);
        PageResponse<BaseRolesDepts> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BaseRolesDepts> save(@RequestBody BaseRolesDepts baseRolesDepts) {
        baseRolesDeptsService.save(baseRolesDepts);
        DetailResponse<BaseRolesDepts> success = ResponseUtil.detailSuccess(baseRolesDepts);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseRolesDeptsId}")
    public DetailResponse<BaseRolesDepts> detail(@PathVariable Long baseRolesDeptsId) {
        BaseRolesDepts baseRolesDepts = new BaseRolesDepts();
        baseRolesDepts.setId(baseRolesDeptsId);
        baseRolesDepts = baseRolesDeptsService.get(baseRolesDepts);
        DetailResponse<BaseRolesDepts> success = ResponseUtil.detailSuccess(baseRolesDepts);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseRolesDeptsId}")
    public CommonResponse delete(@PathVariable Long baseRolesDeptsId) {
        BaseRolesDepts baseRolesDepts = new BaseRolesDepts();
        baseRolesDepts.setId(baseRolesDeptsId);
        int count = baseRolesDeptsService.delete(baseRolesDepts);
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
        BaseRolesDeptsQueryDTO queryDTO = new BaseRolesDeptsQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseRolesDeptsService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
