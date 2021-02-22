package com.github.yoma.stc.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 组态 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-03-30
 */
@Data
@ApiModel("组态表")
public class StcConfigQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "组态名称")
	private String configName;



	@ApiModelProperty(value = "所属项目")
	private Long configProject;


	@ApiModelProperty(value = "所属系统")
	private Long configSystem;



	@ApiModelProperty(value = "组态类型 10 PC 20 移动")
	private Integer configType;


	@ApiModelProperty(value = "组态状态 11 正常 5 隐藏")
	private Integer configStatus;






}
