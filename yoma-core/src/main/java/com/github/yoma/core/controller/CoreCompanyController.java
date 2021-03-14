package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreCompany;
import com.github.yoma.core.dto.CoreCompanyQueryDTO;
import com.github.yoma.core.service.CoreCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 集团/公司RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreCompany")
@Api(tags = "集团/公司")
public class CoreCompanyController extends BaseController {

    @Autowired
    private CoreCompanyService coreCompanyService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreCompany> list(@RequestBody CoreCompanyQueryDTO queryDTO) {
        PageInfo<CoreCompany> pageInfo = coreCompanyService.findPage(queryDTO);
        PageResponse<CoreCompany> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreCompany> save(@RequestBody CoreCompany coreCompany) {
        coreCompanyService.save(coreCompany);
        CommonResponse<CoreCompany> success = ResponseUtil.detailSuccess(coreCompany);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreCompanyId}")
    public CommonResponse<CoreCompany> detail(@PathVariable Long coreCompanyId) {
        CoreCompany coreCompany = new CoreCompany();
        coreCompany.setId(coreCompanyId);
        coreCompany = coreCompanyService.get(coreCompany);
        CommonResponse<CoreCompany> success = ResponseUtil.detailSuccess(coreCompany);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreCompanyId}")
    public CommonResponse delete(@PathVariable Long coreCompanyId) {
        CoreCompany coreCompany = new CoreCompany();
        coreCompany.setId(coreCompanyId);
        int count = coreCompanyService.delete(coreCompany);
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
        CoreCompanyQueryDTO queryDTO = new CoreCompanyQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreCompanyService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
