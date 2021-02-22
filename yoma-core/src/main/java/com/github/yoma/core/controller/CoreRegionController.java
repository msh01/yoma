package com.github.yoma.core.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.CoreRegion;
import com.github.yoma.core.dto.CoreRegionQueryDTO;
import com.github.yoma.core.service.CoreRegionService;
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
 * 行政区域RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreRegion")
@Api(tags = "行政区域")
public class CoreRegionController extends BaseController {

    @Autowired
    private CoreRegionService coreRegionService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public CommonResponse<CoreRegion> list(@RequestBody CoreRegionQueryDTO queryDTO) {
        PageInfo<CoreRegion> pageInfo = coreRegionService.findPage(queryDTO);
        PageResponse<CoreRegion> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public CommonResponse<CoreRegion> save(@RequestBody CoreRegion coreRegion) {
        coreRegionService.save(coreRegion);
        CommonResponse<CoreRegion> success = ResponseUtil.detailSuccess(coreRegion);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreRegionId}")
    @AnonymousAccess
    public CommonResponse<CoreRegion> detail(@PathVariable Long coreRegionId) {
        CoreRegion coreRegion = new CoreRegion();
        coreRegion.setId(coreRegionId);
        coreRegion = coreRegionService.get(coreRegion);
        CommonResponse<CoreRegion> success = ResponseUtil.detailSuccess(coreRegion);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreRegionId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long coreRegionId) {
        CoreRegion coreRegion = new CoreRegion();
        coreRegion.setId(coreRegionId);
        int count = coreRegionService.delete(coreRegion);
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
        CoreRegionQueryDTO queryDTO = new CoreRegionQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreRegionService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
