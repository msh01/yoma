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
import com.github.yoma.core.domain.BaseDictDetail;
import com.github.yoma.core.dto.BaseDictDetailQueryDTO;
import com.github.yoma.core.service.BaseDictDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典详情RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseDictDetail")
@Api(tags = "数据字典详情")
public class BaseDictDetailController extends BaseController {

    @Autowired
    private BaseDictDetailService baseDictDetailService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<BaseDictDetail> list(@RequestBody BaseDictDetailQueryDTO queryDTO) {
        PageInfo<BaseDictDetail> pageInfo = baseDictDetailService.findPage(queryDTO);
        PageResponse<BaseDictDetail> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BaseDictDetail> save(@RequestBody BaseDictDetail baseDictDetail) {
        baseDictDetailService.save(baseDictDetail);
        DetailResponse<BaseDictDetail> success = ResponseUtil.detailSuccess(baseDictDetail);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseDictDetailId}")
    public DetailResponse<BaseDictDetail> detail(@PathVariable Long baseDictDetailId) {
        BaseDictDetail baseDictDetail = new BaseDictDetail();
        baseDictDetail.setId(baseDictDetailId);
        baseDictDetail = baseDictDetailService.get(baseDictDetail);
        DetailResponse<BaseDictDetail> success = ResponseUtil.detailSuccess(baseDictDetail);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseDictDetailId}")
    public CommonResponse delete(@PathVariable Long baseDictDetailId) {
        BaseDictDetail baseDictDetail = new BaseDictDetail();
        baseDictDetail.setId(baseDictDetailId);
        int count = baseDictDetailService.delete(baseDictDetail);
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
        BaseDictDetailQueryDTO queryDTO = new BaseDictDetailQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseDictDetailService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
