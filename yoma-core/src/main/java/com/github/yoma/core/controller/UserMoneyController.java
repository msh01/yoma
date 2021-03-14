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
import com.github.yoma.core.domain.UserMoney;
import com.github.yoma.core.dto.UserMoneyQueryDTO;
import com.github.yoma.core.service.UserMoneyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户资金RestFull服务
 * @author 马世豪
 * @version 2020-12-21
 */
@RestController
@RequestMapping(value = "/core/userMoney")
@Api(tags = "账户资金")
public class UserMoneyController extends BaseController {

	@Autowired
	private UserMoneyService userMoneyService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    public PageResponse<UserMoney> list(@RequestBody UserMoneyQueryDTO queryDTO) {
        PageInfo<UserMoney> pageInfo = userMoneyService.findPage(queryDTO);
        PageResponse<UserMoney> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    public DetailResponse<UserMoney> save(@RequestBody UserMoney userMoney) {
        userMoneyService.save(userMoney);
        DetailResponse<UserMoney> success = ResponseUtil.detailSuccess(userMoney);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{userMoneyId}")
    public DetailResponse<UserMoney> detail(@PathVariable Long userMoneyId) {
        UserMoney  userMoney=new UserMoney();
        userMoney.setId(userMoneyId);
        userMoney = userMoneyService.get(userMoney);
        DetailResponse<UserMoney> success = ResponseUtil.detailSuccess(userMoney);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{userMoneyId}")
    public CommonResponse delete(@PathVariable Long userMoneyId) {
        UserMoney	userMoney=new UserMoney();
		userMoney.setId(userMoneyId);
        int count=userMoneyService.delete(userMoney);
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
		UserMoneyQueryDTO	queryDTO=new UserMoneyQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=userMoneyService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
