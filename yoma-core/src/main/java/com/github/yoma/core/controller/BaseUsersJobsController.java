package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseUsersJobs;
import com.github.yoma.core.dto.BaseUsersJobsQueryDTO;
import com.github.yoma.core.service.BaseUsersJobsService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户岗位关联RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseUsersJobs")
@Api(tags = "用户岗位关联")
public class BaseUsersJobsController extends BaseController {

    @Autowired
    private BaseUsersJobsService baseUsersJobsService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public PageResponse<BaseUsersJobs> list(@RequestBody BaseUsersJobsQueryDTO queryDTO) {
        PageInfo<BaseUsersJobs> pageInfo = baseUsersJobsService.findPage(queryDTO);
        PageResponse<BaseUsersJobs> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseUsersJobs> save(@RequestBody BaseUsersJobs baseUsersJobs) {
        baseUsersJobsService.save(baseUsersJobs);
        DetailResponse<BaseUsersJobs> success = ResponseUtil.detailSuccess(baseUsersJobs);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseUsersJobsId}")
    @AnonymousAccess
    public DetailResponse<BaseUsersJobs> detail(@PathVariable Long baseUsersJobsId) {
        BaseUsersJobs baseUsersJobs = new BaseUsersJobs();
        baseUsersJobs.setId(baseUsersJobsId);
        baseUsersJobs = baseUsersJobsService.get(baseUsersJobs);
        DetailResponse<BaseUsersJobs> success = ResponseUtil.detailSuccess(baseUsersJobs);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseUsersJobsId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseUsersJobsId) {
        BaseUsersJobs baseUsersJobs = new BaseUsersJobs();
        baseUsersJobs.setId(baseUsersJobsId);
        int count = baseUsersJobsService.delete(baseUsersJobs);
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
        BaseUsersJobsQueryDTO queryDTO = new BaseUsersJobsQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseUsersJobsService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
