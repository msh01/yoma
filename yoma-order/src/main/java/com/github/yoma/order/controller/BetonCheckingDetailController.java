package com.github.yoma.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.order.domain.BetonCheckingDetail;
import com.github.yoma.order.dto.BetonCheckingDetailQueryDTO;
import com.github.yoma.order.service.BetonCheckingDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对账明细RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@RestController
@RequestMapping(value = "/order/betonCheckingDetail")
@Api(tags = "对账明细")
public class BetonCheckingDetailController extends BaseController {

    @Autowired
    private BetonCheckingDetailService betonCheckingDetailService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<BetonCheckingDetail> list(BetonCheckingDetailQueryDTO queryDTO) {
        PageInfo<BetonCheckingDetail> pageInfo = betonCheckingDetailService.findPage(queryDTO);
        PageResponse<BetonCheckingDetail> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<BetonCheckingDetail> listPost(@RequestBody BetonCheckingDetailQueryDTO queryDTO) {
        PageInfo<BetonCheckingDetail> pageInfo = betonCheckingDetailService.findPage(queryDTO);
        PageResponse<BetonCheckingDetail> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    @ApiOperation(value = "测试存储过程调用")
    @PostMapping("createBetonCheckingDetail")
    public DetailResponse<BetonCheckingDetail>
        createBetonCheckingDetail(@RequestBody BetonCheckingDetailQueryDTO queryDTO) {
        BetonCheckingDetail betonCheckingDetail = betonCheckingDetailService.createBetonCheckingDetail(queryDTO);
        DetailResponse<BetonCheckingDetail> betonCheckingDetailDetailResponse =
            ResponseUtil.detailSuccess(betonCheckingDetail);
        return betonCheckingDetailDetailResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BetonCheckingDetail> save(@RequestBody BetonCheckingDetailQueryDTO queryDTO) {
        BetonCheckingDetail betonCheckingDetail = betonCheckingDetailService.createBetonCheckingDetail(queryDTO);
        DetailResponse<BetonCheckingDetail> success = ResponseUtil.detailSuccess(betonCheckingDetail);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonCheckingDetailId}")
    public DetailResponse<BetonCheckingDetail> detail(@PathVariable Long betonCheckingDetailId) {
        BetonCheckingDetail betonCheckingDetail = new BetonCheckingDetail();
        betonCheckingDetail.setId(betonCheckingDetailId);
        betonCheckingDetail = betonCheckingDetailService.get(betonCheckingDetail);
        DetailResponse<BetonCheckingDetail> success = ResponseUtil.detailSuccess(betonCheckingDetail);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonCheckingDetailId}")
    public CommonResponse delete(@PathVariable Long betonCheckingDetailId) {
        BetonCheckingDetail betonCheckingDetail = new BetonCheckingDetail();
        betonCheckingDetail.setId(betonCheckingDetailId);
        int count = betonCheckingDetailService.delete(betonCheckingDetail);
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
        BetonCheckingDetailQueryDTO queryDTO = new BetonCheckingDetailQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonCheckingDetailService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
