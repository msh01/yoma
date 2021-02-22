package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工程信息Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-11-20
 */
@Data
@Table(name = "t_beton_project" )
@ApiModel(value = "BetonProject", description="工程信息表")
public class BetonProject extends DataEntity<BetonProject> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "clientId")
	@ApiModelProperty(value = "搅拌站ID")
	private Long clientId;

	@Column(name = "betonClientId")
	@ApiModelProperty(value = "商砼客户ID")
	private Long betonClientId;

	@Column(name = "projectERPNo")
	@ApiModelProperty(value = "ERP中工程编号")
	private String projectERPNo;

	@Column(name = "projectName")
	@ApiModelProperty(value = "工程名称")
	private String projectName;

	@Column(name = "constructionUnit")
	@ApiModelProperty(value = "施工单位")
	private String constructionUnit;

	@Column(name = "addr")
	@ApiModelProperty(value = "项目地址")
	private String addr;

	@Column(name = "parts")
	@ApiModelProperty(value = "施工部位")
	private String parts;

	@Column(name = "conStrength")
	@ApiModelProperty(value = "砼强度")
	private String conStrength;

	@Column(name = "slump")
	@ApiModelProperty(value = "坍落度")
	private String slump;

	@Column(name = "castType")
	@ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
	private Integer castType;

	@Column(name = "cubicNum")
	@ApiModelProperty(value = "计划方量")
	private Double cubicNum;

	@Column(name = "freight")
	@ApiModelProperty(value = "运费(元/方)")
	private Double freight;

	@Column(name = "state")
	@ApiModelProperty(value = "状态 0-进行中 1-结束 99-删除")
	private Integer state;

	@Column(name = "createTime")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@Column(name = "createId")
	@ApiModelProperty(value = "创建人ID")
	private Long createId;

	@Column(name = "createName")
	@ApiModelProperty(value = "创建人姓名")
	private String createName;




}
