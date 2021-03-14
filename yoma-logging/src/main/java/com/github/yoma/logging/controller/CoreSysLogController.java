package com.github.yoma.logging.controller;

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
import com.github.yoma.logging.domain.CoreSysLog;
import com.github.yoma.logging.query.CoreSysLogQueryDTO;
import com.github.yoma.logging.service.CoreSysLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统日志RestFull服务
 * @author 马世豪
 * @version 2020-11-02
 */
@RestController
@RequestMapping(value = "/logging/coreSysLog")
@Api(tags = "系统日志")
public class CoreSysLogController extends BaseController {

	@Autowired
	private CoreSysLogService coreSysLogService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<CoreSysLog> list(@RequestBody CoreSysLogQueryDTO queryDTO) {
        PageInfo<CoreSysLog> pageInfo = coreSysLogService.findPage(queryDTO);
        PageResponse<CoreSysLog> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<CoreSysLog> save(@RequestBody CoreSysLog coreSysLog) {
        coreSysLogService.save(coreSysLog);
        DetailResponse<CoreSysLog> success = ResponseUtil.detailSuccess(coreSysLog);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{coreSysLogId}")
    public DetailResponse<CoreSysLog> detail(@PathVariable Long coreSysLogId) {
        CoreSysLog  coreSysLog=new CoreSysLog();
        coreSysLog.setId(coreSysLogId);
        coreSysLog = coreSysLogService.get(coreSysLog);
        DetailResponse<CoreSysLog> success = ResponseUtil.detailSuccess(coreSysLog);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{coreSysLogId}")
    public CommonResponse delete(@PathVariable Long coreSysLogId) {
        CoreSysLog	coreSysLog=new CoreSysLog();
		coreSysLog.setId(coreSysLogId);
        int count=coreSysLogService.delete(coreSysLog);
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
		CoreSysLogQueryDTO	queryDTO=new CoreSysLogQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=coreSysLogService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
