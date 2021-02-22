package com.github.yoma.order.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;


/**
 * 工程信息 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-11-20
 */
@Data
@ApiModel("工程信息表")
public class BetonProjectQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "搅拌站ID")
	private Long clientId;


	@ApiModelProperty(value = "商砼客户ID")
	private Long betonClientId;


	@ApiModelProperty(value = "ERP中工程编号")
	private String projectERPNo;


	@ApiModelProperty(value = "工程名称")
	private String projectName;


	@ApiModelProperty(value = "施工单位")
	private String constructionUnit;


	@ApiModelProperty(value = "项目地址")
	private String addr;



	@ApiModelProperty(value = "砼强度")
	private String conStrength;


	@ApiModelProperty(value = "坍落度")
	private String slump;


	@ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
	private Integer castType;





	@ApiModelProperty(value = "范围筛选 创建时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "创建人ID")
	private Long createId;


	@ApiModelProperty(value = "创建人姓名")
	private String createName;






}
