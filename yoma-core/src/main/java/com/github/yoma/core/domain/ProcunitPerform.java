package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程环节明细Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@Table(name = "t_procunit_perform" )
@ApiModel(value = "ProcunitPerform", description="流程环节明细表")
public class ProcunitPerform extends DataEntity<ProcunitPerform> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "processCode")
	@ApiModelProperty(value = "流程类型")
	private String processCode;

	@Column(name = "processNo")
	@ApiModelProperty(value = "流程编号")
	private String processNo;

	@Column(name = "linkName")
	@ApiModelProperty(value = "环节编号")
	private String linkName;

	@Column(name = "linkCode")
	@ApiModelProperty(value = "环节名称")
	private String linkCode;

	@Column(name = "notes")
	@ApiModelProperty(value = "备注")
	private String notes;

	@Column(name = "remark1")
	@ApiModelProperty(value = "备用字段")
	private String remark1;

	@Column(name = "createTime")
	@ApiModelProperty(value = "环节发生时间")
	private LocalDateTime createTime;

	@Column(name = "operateId")
	@ApiModelProperty(value = "该环节执行者的ID")
	private Long operateId;

	@Column(name = "operateName")
	@ApiModelProperty(value = "该环节执行者名称")
	private String operateName;




}
