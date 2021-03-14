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
import com.github.yoma.order.domain.BetonCheckingDetailMpty;
import com.github.yoma.order.dto.BetonCheckingDetailMptyQueryDTO;
import com.github.yoma.order.service.BetonCheckingDetailMptyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对账补充明细RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@RestController
@RequestMapping(value = "/order/betonCheckingDetailMpty")
@Api(tags = "对账补充明细")
public class BetonCheckingDetailMptyController extends BaseController {

    @Autowired
    private BetonCheckingDetailMptyService betonCheckingDetailMptyService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<BetonCheckingDetailMpty> list(@RequestBody BetonCheckingDetailMptyQueryDTO queryDTO) {
        PageInfo<BetonCheckingDetailMpty> pageInfo = betonCheckingDetailMptyService.findPage(queryDTO);
        PageResponse<BetonCheckingDetailMpty> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BetonCheckingDetailMpty> save(@RequestBody BetonCheckingDetailMpty betonCheckingDetailMpty) {
        betonCheckingDetailMptyService.save(betonCheckingDetailMpty);
        DetailResponse<BetonCheckingDetailMpty> success = ResponseUtil.detailSuccess(betonCheckingDetailMpty);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonCheckingDetailMptyId}")
    public DetailResponse<BetonCheckingDetailMpty> detail(@PathVariable Long betonCheckingDetailMptyId) {
        BetonCheckingDetailMpty betonCheckingDetailMpty = new BetonCheckingDetailMpty();
        betonCheckingDetailMpty.setId(betonCheckingDetailMptyId);
        betonCheckingDetailMpty = betonCheckingDetailMptyService.get(betonCheckingDetailMpty);
        DetailResponse<BetonCheckingDetailMpty> success = ResponseUtil.detailSuccess(betonCheckingDetailMpty);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonCheckingDetailMptyId}")
    public CommonResponse delete(@PathVariable Long betonCheckingDetailMptyId) {
        BetonCheckingDetailMpty betonCheckingDetailMpty = new BetonCheckingDetailMpty();
        betonCheckingDetailMpty.setId(betonCheckingDetailMptyId);
        int count = betonCheckingDetailMptyService.delete(betonCheckingDetailMpty);
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
        BetonCheckingDetailMptyQueryDTO queryDTO = new BetonCheckingDetailMptyQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonCheckingDetailMptyService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
