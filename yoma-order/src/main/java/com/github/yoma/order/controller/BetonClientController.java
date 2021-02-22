package com.github.yoma.order.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.domain.BetonClient;
import com.github.yoma.order.dto.BetonClientQueryDTO;
import com.github.yoma.order.service.BetonClientService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商砼客户RestFull服务
 *
 * @author 马世豪
 * @version 2020-11-20
 */
@RestController
@RequestMapping(value = "/order/betonClient")
@Api(tags = "商砼客户")
public class BetonClientController extends BaseController {

    @Autowired
    private BetonClientService betonClientService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BetonClient> list(BetonClientQueryDTO queryDTO) {
        PageInfo<BetonClient> pageInfo = betonClientService.findPage(queryDTO);
        PageResponse<BetonClient> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonClient> save(@RequestBody BetonClient betonClient) {
        betonClientService.save(betonClient);
        DetailResponse<BetonClient> success = ResponseUtil.detailSuccess(betonClient);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonClientId}")
    @AnonymousAccess
    public DetailResponse<BetonClient> detail(@PathVariable Long betonClientId) {
        BetonClient betonClient = new BetonClient();
        betonClient.setId(betonClientId);
        betonClient = betonClientService.get(betonClient);
        DetailResponse<BetonClient> success = ResponseUtil.detailSuccess(betonClient);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonClientId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonClientId) {
        BetonClient betonClient = new BetonClient();
        betonClient.setId(betonClientId);
        int count = betonClientService.delete(betonClient);
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
        BetonClientQueryDTO queryDTO = new BetonClientQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonClientService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
