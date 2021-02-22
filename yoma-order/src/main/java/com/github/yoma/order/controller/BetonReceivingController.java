package com.github.yoma.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.domain.BetonReceiving;
import com.github.yoma.order.dto.BetonReceivingQueryDTO;
import com.github.yoma.order.service.BetonReceivingService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商砼销售收款明细RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-09
 */
@RestController
@RequestMapping(value = "/order/betonReceiving")
@Api(tags = "商砼销售收款明细")
public class BetonReceivingController extends BaseController {

    @Autowired
    private BetonReceivingService betonReceivingService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public PageResponse<BetonReceiving> list(@RequestBody BetonReceivingQueryDTO queryDTO) {
        PageInfo<BetonReceiving> pageInfo = betonReceivingService.findPage(queryDTO);
        PageResponse<BetonReceiving> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonReceiving> save(@RequestBody BetonReceiving betonReceiving) {
        betonReceivingService.save(betonReceiving);
        DetailResponse<BetonReceiving> success = ResponseUtil.detailSuccess(betonReceiving);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonReceivingId}")
    @AnonymousAccess
    public DetailResponse<BetonReceiving> detail(@PathVariable Long betonReceivingId) {
        BetonReceiving betonReceiving = new BetonReceiving();
        betonReceiving.setId(betonReceivingId);
        betonReceiving = betonReceivingService.get(betonReceiving);
        DetailResponse<BetonReceiving> success = ResponseUtil.detailSuccess(betonReceiving);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonReceivingId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonReceivingId) {
        BetonReceiving betonReceiving = new BetonReceiving();
        betonReceiving.setId(betonReceivingId);
        int count = betonReceivingService.delete(betonReceiving);
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
        BetonReceivingQueryDTO queryDTO = new BetonReceivingQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonReceivingService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
