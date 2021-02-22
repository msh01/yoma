package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 提现申请Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@Table(name = "t_payment_request" )
@ApiModel(value = "PaymentRequest", description="提现申请表用户在做提现申请时,需要判断用户可提现金额是否足够")
public class PaymentRequest extends DataEntity<PaymentRequest> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "processNo")
	@ApiModelProperty(value = "流程编号")
	private String processNo;

	@Column(name = "applyUserId")
	@ApiModelProperty(value = "申请人ID")
	private Long applyUserId;

	@Column(name = "userType")
	@ApiModelProperty(value = "用户类型 1-搅拌站 2-车辆")
	private Integer userType;

	@Column(name = "applyMoney")
	@ApiModelProperty(value = "申请金额")
	private Double applyMoney;

	@Column(name = "payMoney")
	@ApiModelProperty(value = "实际付款金额")
	private Double payMoney;

	@Column(name = "approvalType")
	@ApiModelProperty(value = "审批状态 0-未审批 1-审批中 2-审批通过 3-审批驳回")
	private Integer approvalType;

	@Column(name = "linkCode")
	@ApiModelProperty(value = "当前工单环节编号")
	private String linkCode;

	@Column(name = "linkName")
	@ApiModelProperty(value = "当前环节名称")
	private String linkName;

	@Column(name = "linkDate")
	@ApiModelProperty(value = "当前环节触发时间")
	private LocalDateTime linkDate;

	@Column(name = "nextLinkCode")
	@ApiModelProperty(value = "下一环节编号")
	private String nextLinkCode;

	@Column(name = "nextLinkName")
	@ApiModelProperty(value = "下一环节名称")
	private String nextLinkName;

	@Column(name = "applyTime")
	@ApiModelProperty(value = "申请时间")
	private LocalDateTime applyTime;

	@Column(name = "overTime")
	@ApiModelProperty(value = "完成时间")
	private LocalDateTime overTime;

	@Column(name = "remark")
	@ApiModelProperty(value = "备注")
	private String remark;




}
