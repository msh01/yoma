package com.github.yoma.order.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.domain.BetonChecking;
import com.github.yoma.order.dto.BetonCheckingQueryDTO;
import com.github.yoma.order.service.BetonCheckingService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对账结果RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@RestController
@RequestMapping(value = "/order/betonChecking")
@Api(tags = "对账结果")
public class BetonCheckingController extends BaseController {

    @Autowired
    private BetonCheckingService betonCheckingService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BetonChecking> list(BetonCheckingQueryDTO queryDTO) {
        PageInfo<BetonChecking> pageInfo = betonCheckingService.findPage(queryDTO);
        PageResponse<BetonChecking> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    // /**
    // * 保存或修改
    // */
    // @ApiOperation(value = " 保存或修改")
    // @PostMapping("/save")
    // @AnonymousAccess
    // public DetailResponse<BetonChecking> save(@RequestBody BetonChecking betonChecking) {
    // betonCheckingService.save(betonChecking);
    // DetailResponse<BetonChecking> success = ResponseUtil.detailSuccess(betonChecking);
    // return success;
    // }

    /**
     * 保存或修改
     */
    @ApiOperation(value = "生成对账单")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonChecking> createBetonChecking(@RequestBody BetonCheckingQueryDTO queryDTO) {
        BetonChecking betonChecking = betonCheckingService.createBetonChecking(queryDTO);
        DetailResponse<BetonChecking> success = ResponseUtil.detailSuccess(betonChecking);
        return success;
    }

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @AnonymousAccess
    public void download(HttpServletResponse response, BetonCheckingQueryDTO queryDTO) throws IOException {
        betonCheckingService.download3(queryDTO, response);
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonCheckingId}")
    @AnonymousAccess
    public DetailResponse<BetonChecking> detail(@PathVariable Long betonCheckingId) {
        BetonChecking betonChecking = new BetonChecking();
        betonChecking.setId(betonCheckingId);
        betonChecking = betonCheckingService.get(betonChecking);
        DetailResponse<BetonChecking> success = ResponseUtil.detailSuccess(betonChecking);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonCheckingId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonCheckingId) {
        BetonChecking betonChecking = new BetonChecking();
        betonChecking.setId(betonCheckingId);
        int count = betonCheckingService.delete(betonChecking);
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
        BetonCheckingQueryDTO queryDTO = new BetonCheckingQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonCheckingService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
