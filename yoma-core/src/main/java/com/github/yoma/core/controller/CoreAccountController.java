package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreAccount;
import com.github.yoma.core.dto.CoreAccountQueryDTO;
import com.github.yoma.core.service.CoreAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户信息RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreAccount")
@Api(tags = "账户信息")
public class CoreAccountController extends BaseController {

    @Autowired
    private CoreAccountService coreAccountService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreAccount> list(@RequestBody CoreAccountQueryDTO queryDTO) {
        PageInfo<CoreAccount> pageInfo = coreAccountService.findPage(queryDTO);
        PageResponse<CoreAccount> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreAccount> save(@RequestBody CoreAccount coreAccount) {
        coreAccountService.save(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }



    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreAccountId}")
    public CommonResponse<CoreAccount> detail(@PathVariable Long coreAccountId) {
        CoreAccount coreAccount = new CoreAccount();
        coreAccount.setId(coreAccountId);
        coreAccount = coreAccountService.get(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreAccountId}")
    public CommonResponse delete(@PathVariable Long coreAccountId) {
        CoreAccount coreAccount = new CoreAccount();
        coreAccount.setId(coreAccountId);
        int count = coreAccountService.delete(coreAccount);
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
        CoreAccountQueryDTO queryDTO = new CoreAccountQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreAccountService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
