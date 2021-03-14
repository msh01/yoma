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
import com.github.yoma.order.domain.ClientInfor;
import com.github.yoma.order.dto.ClientInforQueryDTO;
import com.github.yoma.order.service.ClientInforService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 搅拌站登录用户RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-03
 */
@RestController
@RequestMapping(value = "/order/clientInfor")
@Api(tags = "搅拌站登录用户")
public class ClientInforController extends BaseController {

    @Autowired
    private ClientInforService clientInforService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<ClientInfor> list(ClientInforQueryDTO queryDTO) {
        PageInfo<ClientInfor> pageInfo = clientInforService.findPage(queryDTO);
        PageResponse<ClientInfor> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<ClientInfor> save(@RequestBody ClientInfor clientInfor) {
        clientInforService.save(clientInfor);
        DetailResponse<ClientInfor> success = ResponseUtil.detailSuccess(clientInfor);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{clientInforId}")
    public DetailResponse<ClientInfor> detail(@PathVariable Long clientInforId) {
        ClientInfor clientInfor = new ClientInfor();
        clientInfor.setId(clientInforId);
        clientInfor = clientInforService.get(clientInfor);
        DetailResponse<ClientInfor> success = ResponseUtil.detailSuccess(clientInfor);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{clientInforId}")
    public CommonResponse delete(@PathVariable Long clientInforId) {
        ClientInfor clientInfor = new ClientInfor();
        clientInfor.setId(clientInforId);
        int count = clientInforService.delete(clientInfor);
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
        ClientInforQueryDTO queryDTO = new ClientInforQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = clientInforService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
