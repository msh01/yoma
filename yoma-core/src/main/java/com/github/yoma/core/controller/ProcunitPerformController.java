package com.github.yoma.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.core.domain.ProcunitPerform;
import com.github.yoma.core.dto.ProcunitPerformQueryDTO;
import com.github.yoma.core.service.ProcunitPerformService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 流程环节明细RestFull服务
 * @author 马世豪
 * @version 2020-12-21
 */
@RestController
@RequestMapping(value = "/core/procunitPerform")
@Api(tags = "流程环节明细")
public class ProcunitPerformController extends BaseController {

	@Autowired
	private ProcunitPerformService procunitPerformService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<ProcunitPerform> list(@RequestBody ProcunitPerformQueryDTO queryDTO) {
        PageInfo<ProcunitPerform> pageInfo = procunitPerformService.findPage(queryDTO);
        PageResponse<ProcunitPerform> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<ProcunitPerform> save(@RequestBody ProcunitPerform procunitPerform) {
        procunitPerformService.save(procunitPerform);
        DetailResponse<ProcunitPerform> success = ResponseUtil.detailSuccess(procunitPerform);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{procunitPerformId}")
    public DetailResponse<ProcunitPerform> detail(@PathVariable Long procunitPerformId) {
        ProcunitPerform  procunitPerform=new ProcunitPerform();
        procunitPerform.setId(procunitPerformId);
        procunitPerform = procunitPerformService.get(procunitPerform);
        DetailResponse<ProcunitPerform> success = ResponseUtil.detailSuccess(procunitPerform);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{procunitPerformId}")
    public CommonResponse delete(@PathVariable Long procunitPerformId) {
        ProcunitPerform	procunitPerform=new ProcunitPerform();
		procunitPerform.setId(procunitPerformId);
        int count=procunitPerformService.delete(procunitPerform);
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
		ProcunitPerformQueryDTO	queryDTO=new ProcunitPerformQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=procunitPerformService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
