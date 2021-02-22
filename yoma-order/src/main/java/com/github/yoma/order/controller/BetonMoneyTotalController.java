package com.github.yoma.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.domain.BetonMoneyTotal;
import com.github.yoma.order.dto.BetonMoneyTotalQueryDTO;
import com.github.yoma.order.service.BetonMoneyTotalService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商砼收付款累计RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-16
 */
@RestController
@RequestMapping(value = "/order/betonMoneyTotal")
@Api(tags = "商砼收付款累计")
public class BetonMoneyTotalController extends BaseController {

    @Autowired
    private BetonMoneyTotalService betonMoneyTotalService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public PageResponse<BetonMoneyTotal> list(@RequestBody BetonMoneyTotalQueryDTO queryDTO) {
        PageInfo<BetonMoneyTotal> pageInfo = betonMoneyTotalService.findPage(queryDTO);
        PageResponse<BetonMoneyTotal> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonMoneyTotal> save(@RequestBody BetonMoneyTotal betonMoneyTotal) {
        betonMoneyTotalService.save(betonMoneyTotal);
        DetailResponse<BetonMoneyTotal> success = ResponseUtil.detailSuccess(betonMoneyTotal);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonMoneyTotalId}")
    @AnonymousAccess
    public DetailResponse<BetonMoneyTotal> detail(@PathVariable Long betonMoneyTotalId) {
        BetonMoneyTotal betonMoneyTotal = new BetonMoneyTotal();
        betonMoneyTotal.setId(betonMoneyTotalId);
        betonMoneyTotal = betonMoneyTotalService.get(betonMoneyTotal);
        DetailResponse<BetonMoneyTotal> success = ResponseUtil.detailSuccess(betonMoneyTotal);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonMoneyTotalId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonMoneyTotalId) {
        BetonMoneyTotal betonMoneyTotal = new BetonMoneyTotal();
        betonMoneyTotal.setId(betonMoneyTotalId);
        int count = betonMoneyTotalService.delete(betonMoneyTotal);
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
        BetonMoneyTotalQueryDTO queryDTO = new BetonMoneyTotalQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonMoneyTotalService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
