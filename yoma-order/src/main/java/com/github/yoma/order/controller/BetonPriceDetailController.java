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
import com.github.yoma.order.domain.BetonPriceDetail;
import com.github.yoma.order.dto.BetonPriceDetailQueryDTO;
import com.github.yoma.order.service.BetonPriceDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 砼标号价格清单RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@RestController
@RequestMapping(value = "/order/betonPriceDetail")
@Api(tags = "砼标号价格清单")
public class BetonPriceDetailController extends BaseController {

    @Autowired
    private BetonPriceDetailService betonPriceDetailService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<BetonPriceDetail> list(@RequestBody BetonPriceDetailQueryDTO queryDTO) {
        PageInfo<BetonPriceDetail> pageInfo = betonPriceDetailService.findPage(queryDTO);
        PageResponse<BetonPriceDetail> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BetonPriceDetail> save(@RequestBody BetonPriceDetail betonPriceDetail) {
        betonPriceDetailService.save(betonPriceDetail);
        DetailResponse<BetonPriceDetail> success = ResponseUtil.detailSuccess(betonPriceDetail);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonPriceDetailId}")
    public DetailResponse<BetonPriceDetail> detail(@PathVariable Long betonPriceDetailId) {
        BetonPriceDetail betonPriceDetail = new BetonPriceDetail();
        betonPriceDetail.setId(betonPriceDetailId);
        betonPriceDetail = betonPriceDetailService.get(betonPriceDetail);
        DetailResponse<BetonPriceDetail> success = ResponseUtil.detailSuccess(betonPriceDetail);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonPriceDetailId}")
    public CommonResponse delete(@PathVariable Long betonPriceDetailId) {
        BetonPriceDetail betonPriceDetail = new BetonPriceDetail();
        betonPriceDetail.setId(betonPriceDetailId);
        int count = betonPriceDetailService.delete(betonPriceDetail);
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
        BetonPriceDetailQueryDTO queryDTO = new BetonPriceDetailQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonPriceDetailService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
