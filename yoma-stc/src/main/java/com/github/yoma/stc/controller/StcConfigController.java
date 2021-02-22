package com.github.yoma.stc.controller;

import com.github.pagehelper.PageInfo;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.stc.domain.StcConfig;
import com.github.yoma.stc.dto.StcConfigQueryDTO;
import com.github.yoma.stc.service.StcConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组态RestFull服务
 * @author 马世豪
 * @version 2020-03-30
 */
@RestController
@RequestMapping(value = "/widget/stcConfig")
@Api(tags = "组态")
public class StcConfigController extends BaseController {

	@Autowired
	private StcConfigService stcConfigService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public CommonResponse<StcConfig> list(@RequestBody StcConfigQueryDTO queryDTO) {
        PageInfo<StcConfig> pageInfo = stcConfigService.findPage(queryDTO);
        PageResponse<StcConfig> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public CommonResponse<StcConfig> save(@RequestBody StcConfig stcConfig) {
        stcConfigService.save(stcConfig);
        CommonResponse<StcConfig> success = ResponseUtil.success();
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{stcConfigId}")
    @AnonymousAccess
    public CommonResponse<StcConfig> detail(@PathVariable Long stcConfigId) {
        StcConfig  stcConfig=new StcConfig();
        stcConfig.setId(stcConfigId);
        stcConfig = stcConfigService.get(stcConfig);
        CommonResponse<StcConfig> success = ResponseUtil.detailSuccess(stcConfig);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{stcConfigId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long stcConfigId) {
        StcConfig	stcConfig=new StcConfig();
		stcConfig.setId(stcConfigId);
        int count=stcConfigService.delete(stcConfig);
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
		StcConfigQueryDTO	queryDTO=new StcConfigQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=stcConfigService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
