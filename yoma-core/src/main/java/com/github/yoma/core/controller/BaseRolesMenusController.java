package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.BaseRolesMenus;
import com.github.yoma.core.dto.BaseRolesMenusQueryDTO;
import com.github.yoma.core.service.BaseRolesMenusService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色菜单关联RestFull服务
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@RestController
@RequestMapping(value = "/core/baseRolesMenus")
@Api(tags = "角色菜单关联")
public class BaseRolesMenusController extends BaseController {

    @Autowired
    private BaseRolesMenusService baseRolesMenusService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public PageResponse<BaseRolesMenus> list(@RequestBody BaseRolesMenusQueryDTO queryDTO) {
        PageInfo<BaseRolesMenus> pageInfo = baseRolesMenusService.findPage(queryDTO);
        PageResponse<BaseRolesMenus> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BaseRolesMenus> save(@RequestBody BaseRolesMenus baseRolesMenus) {
        baseRolesMenusService.save(baseRolesMenus);
        DetailResponse<BaseRolesMenus> success = ResponseUtil.detailSuccess(baseRolesMenus);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{baseRolesMenusId}")
    @AnonymousAccess
    public DetailResponse<BaseRolesMenus> detail(@PathVariable Long baseRolesMenusId) {
        BaseRolesMenus baseRolesMenus = new BaseRolesMenus();
        baseRolesMenus.setId(baseRolesMenusId);
        baseRolesMenus = baseRolesMenusService.get(baseRolesMenus);
        DetailResponse<BaseRolesMenus> success = ResponseUtil.detailSuccess(baseRolesMenus);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{baseRolesMenusId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long baseRolesMenusId) {
        BaseRolesMenus baseRolesMenus = new BaseRolesMenus();
        baseRolesMenus.setId(baseRolesMenusId);
        int count = baseRolesMenusService.delete(baseRolesMenus);
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
        BaseRolesMenusQueryDTO queryDTO = new BaseRolesMenusQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = baseRolesMenusService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
