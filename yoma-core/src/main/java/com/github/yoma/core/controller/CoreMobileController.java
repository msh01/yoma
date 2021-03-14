package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreMobile;
import com.github.yoma.core.dto.CoreMobileQueryDTO;
import com.github.yoma.core.service.CoreMobileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 手机等移动设备RestFull服务
 *
 * @author 马世豪
 * @version 2020-05-22
 */
@RestController
@RequestMapping(value = "/core/coreMobile")
@Api(tags = "手机等移动设备")
public class CoreMobileController extends BaseController {

    @Autowired
    private CoreMobileService coreMobileService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreMobile> list(@RequestBody CoreMobileQueryDTO queryDTO) {
        PageInfo<CoreMobile> pageInfo = coreMobileService.findPage(queryDTO);
        PageResponse<CoreMobile> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreMobile> save(@RequestBody CoreMobile coreMobile) {
        coreMobileService.save(coreMobile);
        CommonResponse<CoreMobile> success = ResponseUtil.detailSuccess(coreMobile);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreMobileId}")
    public CommonResponse<CoreMobile> detail(@PathVariable Long coreMobileId) {
        CoreMobile coreMobile = new CoreMobile();
        coreMobile.setId(coreMobileId);
        coreMobile = coreMobileService.get(coreMobile);
        CommonResponse<CoreMobile> success = ResponseUtil.detailSuccess(coreMobile);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreMobileId}")
    public CommonResponse delete(@PathVariable Long coreMobileId) {
        CoreMobile coreMobile = new CoreMobile();
        coreMobile.setId(coreMobileId);
        int count = coreMobileService.delete(coreMobile);
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
        CoreMobileQueryDTO queryDTO = new CoreMobileQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreMobileService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
