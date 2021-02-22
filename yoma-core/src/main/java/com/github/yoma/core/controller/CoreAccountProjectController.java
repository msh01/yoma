package com.github.yoma.core.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.CoreAccountProject;
import com.github.yoma.core.dto.CoreAccountProjectQueryDTO;
import com.github.yoma.core.service.CoreAccountProjectService;
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
 * 项目角色关系RestFull服务
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@RestController
@RequestMapping(value = "/core/coreAccountProject")
@Api(tags = "项目角色关系")
public class CoreAccountProjectController extends BaseController {

    @Autowired
    private CoreAccountProjectService coreAccountProjectService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public CommonResponse<CoreAccountProject> list(@RequestBody CoreAccountProjectQueryDTO queryDTO) {
        PageInfo<CoreAccountProject> pageInfo = coreAccountProjectService.findPage(queryDTO);
        PageResponse<CoreAccountProject> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public CommonResponse<CoreAccountProject> save(@RequestBody CoreAccountProject coreAccountProject) {
        coreAccountProjectService.save(coreAccountProject);
        CommonResponse<CoreAccountProject> success = ResponseUtil.detailSuccess(coreAccountProject);
        return success;
    }

    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreAccountProjectId}")
    @AnonymousAccess
    public CommonResponse<CoreAccountProject> detail(@PathVariable Long coreAccountProjectId) {
        CoreAccountProject coreAccountProject = new CoreAccountProject();
        coreAccountProject.setId(coreAccountProjectId);
        coreAccountProject = coreAccountProjectService.get(coreAccountProject);
        CommonResponse<CoreAccountProject> success = ResponseUtil.detailSuccess(coreAccountProject);
        return success;
    }

    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreAccountProjectId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long coreAccountProjectId) {
        CoreAccountProject coreAccountProject = new CoreAccountProject();
        coreAccountProject.setId(coreAccountProjectId);
        int count = coreAccountProjectService.delete(coreAccountProject);
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
        CoreAccountProjectQueryDTO queryDTO = new CoreAccountProjectQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = coreAccountProjectService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }

}
