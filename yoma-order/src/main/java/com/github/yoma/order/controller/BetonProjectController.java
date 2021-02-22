package com.github.yoma.order.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.order.domain.BetonProject;
import com.github.yoma.order.dto.BetonProjectQueryDTO;
import com.github.yoma.order.service.BetonProjectService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工程信息RestFull服务
 * @author 马世豪
 * @version 2020-11-20
 */
@RestController
@RequestMapping(value = "/order/betonProject")
@Api(tags = "工程信息")
public class BetonProjectController extends BaseController {

	@Autowired
	private BetonProjectService betonProjectService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<BetonProject> list(  BetonProjectQueryDTO queryDTO) {
        PageInfo<BetonProject> pageInfo = betonProjectService.findPage(queryDTO);
        PageResponse<BetonProject> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<BetonProject> save(@RequestBody BetonProject betonProject) {
        betonProjectService.save(betonProject);
        DetailResponse<BetonProject> success = ResponseUtil.detailSuccess(betonProject);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{betonProjectId}")
    @AnonymousAccess
    public DetailResponse<BetonProject> detail(@PathVariable Long betonProjectId) {
        BetonProject  betonProject=new BetonProject();
        betonProject.setId(betonProjectId);
        betonProject = betonProjectService.get(betonProject);
        DetailResponse<BetonProject> success = ResponseUtil.detailSuccess(betonProject);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{betonProjectId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long betonProjectId) {
        BetonProject	betonProject=new BetonProject();
		betonProject.setId(betonProjectId);
        int count=betonProjectService.delete(betonProject);
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
		BetonProjectQueryDTO	queryDTO=new BetonProjectQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=betonProjectService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
