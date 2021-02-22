package com.github.yoma.core.controller;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.PaymentRequest;
import com.github.yoma.core.dto.PaymentRequestQueryDTO;
import com.github.yoma.core.service.PaymentRequestService;
import com.github.yoma.common.persistence.BaseController;
import com.github.yoma.common.persistence.BatchDTO;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.PageResponse;
import com.github.yoma.common.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 提现申请RestFull服务
 * @author 马世豪
 * @version 2020-12-21
 */
@RestController
@RequestMapping(value = "/core/paymentRequest")
@Api(tags = "提现申请")
public class PaymentRequestController extends BaseController {

	@Autowired
	private PaymentRequestService paymentRequestService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @PostMapping("list")
    @AnonymousAccess
    public PageResponse<PaymentRequest> list(@RequestBody PaymentRequestQueryDTO queryDTO) {
        PageInfo<PaymentRequest> pageInfo = paymentRequestService.findPage(queryDTO);
        PageResponse<PaymentRequest> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

	  /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<PaymentRequest> save(@RequestBody PaymentRequest paymentRequest) {
        paymentRequestService.save(paymentRequest);
        DetailResponse<PaymentRequest> success = ResponseUtil.detailSuccess(paymentRequest);
        return success;
    }


	/**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{paymentRequestId}")
    @AnonymousAccess
    public DetailResponse<PaymentRequest> detail(@PathVariable Long paymentRequestId) {
        PaymentRequest  paymentRequest=new PaymentRequest();
        paymentRequest.setId(paymentRequestId);
        paymentRequest = paymentRequestService.get(paymentRequest);
        DetailResponse<PaymentRequest> success = ResponseUtil.detailSuccess(paymentRequest);
        return success;
    }


	/**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{paymentRequestId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long paymentRequestId) {
        PaymentRequest	paymentRequest=new PaymentRequest();
		paymentRequest.setId(paymentRequestId);
        int count=paymentRequestService.delete(paymentRequest);
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
		PaymentRequestQueryDTO	queryDTO=new PaymentRequestQueryDTO();

		queryDTO.setBatchIdList(batchDTO.getBatchIdList());
		int count=paymentRequestService.batchDelete(queryDTO);
		CommonResponse success = ResponseUtil.success();
		return success;
	}


}
