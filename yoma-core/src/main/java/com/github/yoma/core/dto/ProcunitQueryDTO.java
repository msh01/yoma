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
 * 流程环节配置 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@ApiModel("流程环节配置表")
public class ProcunitQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "流程类型 01-订单流程")
	private String processType;


	@ApiModelProperty(value = "环节名称")
	private String linkName;


	@ApiModelProperty(value = "环节编号")
	private String linkCode;


	@ApiModelProperty(value = "下一环节名称")
	private String nextLinkName;


	@ApiModelProperty(value = "下一环节编号")
	private String nextLinkCode;


	@ApiModelProperty(value = "流程环节标识 2-环节")
	private Integer puType;



	@ApiModelProperty(value = "状态 0-正常 1-关闭 99-删除")
	private Integer state;


	@ApiModelProperty(value = "范围筛选 创建时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "范围筛选 修改时间开始")
	private LocalDateTime beginUpdTime;

    @ApiModelProperty(value = "范围筛选 修改时间结束")
	private LocalDateTime endUpdTime;






}
