package com.github.yoma.core.dto;

import javax.persistence.*;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;


/**
 * 提现申请 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@ApiModel("提现申请表用户在做提现申请时,需要判断用户可提现金额是否足够")
public class PaymentRequestQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "流程编号")
	private String processNo;


	@ApiModelProperty(value = "申请人ID")
	private Long applyUserId;


	@ApiModelProperty(value = "用户类型 1-搅拌站 2-车辆")
	private Integer userType;




	@ApiModelProperty(value = "审批状态 0-未审批 1-审批中 2-审批通过 3-审批驳回")
	private Integer approvalType;


	@ApiModelProperty(value = "当前工单环节编号")
	private String linkCode;


	@ApiModelProperty(value = "当前环节名称")
	private String linkName;


	@ApiModelProperty(value = "范围筛选 当前环节触发时间开始")
	private LocalDateTime beginLinkDate;

    @ApiModelProperty(value = "范围筛选 当前环节触发时间结束")
	private LocalDateTime endLinkDate;


	@ApiModelProperty(value = "下一环节编号")
	private String nextLinkCode;


	@ApiModelProperty(value = "下一环节名称")
	private String nextLinkName;


	@ApiModelProperty(value = "范围筛选 申请时间开始")
	private LocalDateTime beginApplyTime;

    @ApiModelProperty(value = "范围筛选 申请时间结束")
	private LocalDateTime endApplyTime;


	@ApiModelProperty(value = "范围筛选 完成时间开始")
	private LocalDateTime beginOverTime;

    @ApiModelProperty(value = "范围筛选 完成时间结束")
	private LocalDateTime endOverTime;







}
