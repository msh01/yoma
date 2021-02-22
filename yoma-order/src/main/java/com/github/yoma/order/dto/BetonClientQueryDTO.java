package com.github.yoma.order.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;


/**
 * 商砼客户 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-11-20
 */
@Data
@ApiModel("商砼客户列表")
public class BetonClientQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "客户编号")
	private String clientNo;


	@ApiModelProperty(value = "客户名称")
	private String clientName;


	@ApiModelProperty(value = "客户类型 1-普通客户")
	private Integer clientType;


	@ApiModelProperty(value = "性别 1-男 2-女")
	private Integer sex;


	@ApiModelProperty(value = "审核状态 0-审核通过 1-未审核 2-审核未通过")
	private Integer checkType;


	@ApiModelProperty(value = "状态 0-正常 99-删除")
	private Integer state;


	@ApiModelProperty(value = "范围筛选 创建时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "创建人ID")
	private Long createId;


	@ApiModelProperty(value = "创建人姓名")
	private String createName;






}
