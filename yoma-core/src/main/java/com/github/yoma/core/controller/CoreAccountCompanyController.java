package com.github.yoma.core.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.CoreAccountCompany;
import com.github.yoma.core.dto.CoreAccountCompanyQueryDTO;
import com.github.yoma.core.service.CoreAccountCompanyService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 集团账户关系RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreAccountCompany")
@Api(tags = "集团账户关系")
public class CoreAccountCompanyController extends BaseController {

    @Autowired
    private CoreAccountCompanyService coreAccountCompanyService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public CommonResponse<CoreAccountCompany> list(@RequestBody CoreAccountCompanyQueryDTO queryDTO) {
        PageInfo<CoreAccountCompany> pageInfo = coreAccountCompanyService.findPage(queryDTO);
        PageResponse<CoreAccountCompany> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public CommonResponse<CoreAccountCompany> save(@RequestBody CoreAccountCompany coreAccountCompany) {
        coreAccountCompanyService.save(coreAccountCompany);
        CommonResponse<CoreAccountCompany> success = ResponseUtil.detailSuccess(coreAccountCompany);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreAccountCompanyId}")
    @AnonymousAccess
    public CommonResponse<CoreAccountCompany> detail(@PathVariable Long coreAccountCompanyId) {
        CoreAccountCompany coreAccountCompany = new CoreAccountCompany();
        coreAccountCompany.setId(coreAccountCompanyId);
        coreAccountCompany = coreAccountCompanyService.get(coreAccountCompany);
        CommonResponse<CoreAccountCompany> success = ResponseUtil.detailSuccess(coreAccountCompany);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreAccountCompanyId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long coreAccountCompanyId) {
        CoreAccountCompany coreAccountCompany = new CoreAccountCompany();
        coreAccountCompany.setId(coreAccountCompanyId);
        int count = coreAccountCompanyService.delete(coreAccountCompany);
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
        CoreAccountCompanyQueryDTO queryDTO = new CoreAccountCompanyQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreAccountCompanyService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
