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
import com.github.yoma.core.domain.Procunit;
import com.github.yoma.core.dto.ProcunitQueryDTO;
import com.github.yoma.core.service.ProcunitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 流程环节配置RestFull服务
 * @author 马世豪
 * @version 2020-12-21
 */
@RestController
@RequestMapping(value = "/core/procunit")
@Api(tags = "流程环节配置")
public class ProcunitController extends BaseController {

	@Autowired
	private ProcunitService procunitService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<Procunit> list(@RequestBody ProcunitQueryDTO queryDTO) {
        PageInfo<Procunit> pageInfo = procunitService.findPage(queryDTO);
        PageResponse<Procunit> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<Procunit> save(@RequestBody Procunit procunit) {
        procunitService.save(procunit);
        DetailResponse<Procunit> success = ResponseUtil.detailSuccess(procunit);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{procunitId}")
    public DetailResponse<Procunit> detail(@PathVariable Long procunitId) {
        Procunit  procunit=new Procunit();
        procunit.setId(procunitId);
        procunit = procunitService.get(procunit);
        DetailResponse<Procunit> success = ResponseUtil.detailSuccess(procunit);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{procunitId}")
    public CommonResponse delete(@PathVariable Long procunitId) {
        Procunit	procunit=new Procunit();
		procunit.setId(procunitId);
        int count=procunitService.delete(procunit);
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
		ProcunitQueryDTO	queryDTO=new ProcunitQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=procunitService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
