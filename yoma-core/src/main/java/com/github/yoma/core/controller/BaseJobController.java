package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseJob;
import com.github.yoma.core.dto.BaseJobQueryDTO;
import com.github.yoma.core.service.BaseJobService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 岗位RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseJob")
@Api(tags = "岗位")
public class BaseJobController extends BaseController {

    @Autowired
    private BaseJobService baseJobService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BaseJob> list(BaseJobQueryDTO queryDTO) {
        PageInfo<BaseJob> pageInfo = baseJobService.findPage(queryDTO);
        PageResponse<BaseJob> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseJob> save(@RequestBody BaseJob baseJob) {
        baseJobService.save(baseJob);
        DetailResponse<BaseJob> success = ResponseUtil.detailSuccess(baseJob);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseJobId}")
    @AnonymousAccess
    public DetailResponse<BaseJob> detail(@PathVariable Long baseJobId) {
        BaseJob baseJob = new BaseJob();
        baseJob.setId(baseJobId);
        baseJob = baseJobService.get(baseJob);
        DetailResponse<BaseJob> success = ResponseUtil.detailSuccess(baseJob);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseJobId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseJobId) {
        BaseJob baseJob = new BaseJob();
        baseJob.setId(baseJobId);
        int count = baseJobService.delete(baseJob);
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
        BaseJobQueryDTO queryDTO = new BaseJobQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseJobService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
