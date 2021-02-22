package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程环节配置Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@Table(name = "t_procunit" )
@ApiModel(value = "Procunit", description="流程环节配置表")
public class Procunit extends DataEntity<Procunit> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "processType")
	@ApiModelProperty(value = "流程类型 01-订单流程")
	private String processType;

	@Column(name = "linkName")
	@ApiModelProperty(value = "环节名称")
	private String linkName;

	@Column(name = "linkCode")
	@ApiModelProperty(value = "环节编号")
	private String linkCode;

	@Column(name = "nextLinkName")
	@ApiModelProperty(value = "下一环节名称")
	private String nextLinkName;

	@Column(name = "nextLinkCode")
	@ApiModelProperty(value = "下一环节编号")
	private String nextLinkCode;

	@Column(name = "puType")
	@ApiModelProperty(value = "流程环节标识 2-环节")
	private Integer puType;

	@Column(name = "sort")
	@ApiModelProperty(value = "排序 用于记录环节的前后顺序")
	private Integer sort;

	@Column(name = "state")
	@ApiModelProperty(value = "状态 0-正常 1-关闭 99-删除")
	private Integer state;

	@Column(name = "createTime")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@Column(name = "updTime")
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updTime;




}
