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
import com.github.yoma.order.domain.BetonCheckingSetting;
import com.github.yoma.order.dto.BetonCheckingSettingQueryDTO;
import com.github.yoma.order.service.BetonCheckingSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对账周期RestFull服务
 *
 * @author 马世豪
 * @version 2020-11-30
 */
@RestController
@RequestMapping(value = "/order/betonCheckingSetting")
@Api(tags = "对账周期")
public class BetonCheckingSettingController extends BaseController {

    @Autowired
    private BetonCheckingSettingService betonCheckingSettingService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    public PageResponse<BetonCheckingSetting> list(BetonCheckingSettingQueryDTO queryDTO) {
        PageInfo<BetonCheckingSetting> pageInfo = betonCheckingSettingService.findPage(queryDTO);
        PageResponse<BetonCheckingSetting> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<BetonCheckingSetting> save(@RequestBody BetonCheckingSetting betonCheckingSetting) {
        betonCheckingSettingService.save(betonCheckingSetting);
        DetailResponse<BetonCheckingSetting> success = ResponseUtil.detailSuccess(betonCheckingSetting);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonCheckingSettingId}")
    public DetailResponse<BetonCheckingSetting> detail(@PathVariable Long betonCheckingSettingId) {
        BetonCheckingSetting betonCheckingSetting = new BetonCheckingSetting();
        betonCheckingSetting.setId(betonCheckingSettingId);
        betonCheckingSetting = betonCheckingSettingService.get(betonCheckingSetting);
        DetailResponse<BetonCheckingSetting> success = ResponseUtil.detailSuccess(betonCheckingSetting);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonCheckingSettingId}")
    public CommonResponse delete(@PathVariable Long betonCheckingSettingId) {
        BetonCheckingSetting betonCheckingSetting = new BetonCheckingSetting();
        betonCheckingSetting.setId(betonCheckingSettingId);
        int count = betonCheckingSettingService.delete(betonCheckingSetting);
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
        BetonCheckingSettingQueryDTO queryDTO = new BetonCheckingSettingQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = betonCheckingSettingService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
