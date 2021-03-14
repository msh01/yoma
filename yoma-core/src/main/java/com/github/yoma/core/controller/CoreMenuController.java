package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.CoreMenu;
import com.github.yoma.core.dto.CoreMenuQueryDTO;
import com.github.yoma.core.service.CoreMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单和操作按钮RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreMenu")
@Api(tags = "菜单和操作按钮")
public class CoreMenuController extends BaseController {

    @Autowired
    private CoreMenuService coreMenuService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public CommonResponse<CoreMenu> list(@RequestBody CoreMenuQueryDTO queryDTO) {
        PageInfo<CoreMenu> pageInfo = coreMenuService.findPage(queryDTO);
        PageResponse<CoreMenu> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public CommonResponse<CoreMenu> save(@RequestBody CoreMenu coreMenu) {
        coreMenuService.save(coreMenu);
        CommonResponse<CoreMenu> success = ResponseUtil.detailSuccess(coreMenu);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreMenuId}")
    public CommonResponse<CoreMenu> detail(@PathVariable Long coreMenuId) {
        CoreMenu coreMenu = new CoreMenu();
        coreMenu.setId(coreMenuId);
        coreMenu = coreMenuService.get(coreMenu);
        CommonResponse<CoreMenu> success = ResponseUtil.detailSuccess(coreMenu);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreMenuId}")
    public CommonResponse delete(@PathVariable Long coreMenuId) {
        CoreMenu coreMenu = new CoreMenu();
        coreMenu.setId(coreMenuId);
        int count = coreMenuService.delete(coreMenu);
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
        CoreMenuQueryDTO queryDTO = new CoreMenuQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreMenuService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
