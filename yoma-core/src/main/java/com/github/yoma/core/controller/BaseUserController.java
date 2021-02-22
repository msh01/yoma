package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseUser;
import com.github.yoma.core.dto.BaseUserQueryDTO;
import com.github.yoma.core.service.BaseUserService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统用户RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseUser")
@Api(tags = "系统用户")
public class BaseUserController extends BaseController {

    @Autowired
    private BaseUserService baseUserService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BaseUser> list(BaseUserQueryDTO queryDTO) {
        PageInfo<BaseUser> pageInfo = baseUserService.findPage(queryDTO);
        PageResponse<BaseUser> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseUser> save(@RequestBody BaseUser baseUser) {
        baseUserService.save(baseUser);
        DetailResponse<BaseUser> success = ResponseUtil.detailSuccess(baseUser);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseUserId}")
    @AnonymousAccess
    public DetailResponse<BaseUser> detail(@PathVariable Long baseUserId) {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(baseUserId);
        baseUser = baseUserService.get(baseUser);
        DetailResponse<BaseUser> success = ResponseUtil.detailSuccess(baseUser);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseUserId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseUserId) {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(baseUserId);
        int count = baseUserService.delete(baseUser);
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
        BaseUserQueryDTO queryDTO = new BaseUserQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseUserService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
