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
 * 流程环节明细 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@ApiModel("流程环节明细表")
public class ProcunitPerformQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "流程类型")
	private String processCode;


	@ApiModelProperty(value = "流程编号")
	private String processNo;


	@ApiModelProperty(value = "环节编号")
	private String linkName;


	@ApiModelProperty(value = "环节名称")
	private String linkCode;




	@ApiModelProperty(value = "范围筛选 环节发生时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 环节发生时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "范围筛选 该环节执行者的ID开始")
	private Long beginOperateId;

    @ApiModelProperty(value = "范围筛选 该环节执行者的ID结束")
	private Long endOperateId;


	@ApiModelProperty(value = "范围筛选 该环节执行者名称开始")
	private String beginOperateName;

    @ApiModelProperty(value = "范围筛选 该环节执行者名称结束")
	private String endOperateName;






}
