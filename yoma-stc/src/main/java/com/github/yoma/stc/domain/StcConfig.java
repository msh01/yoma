package com.github.yoma.stc.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组态Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-03-30
 */
@Data
@Table(name = "stc_config" )
@ApiModel(value = "StcConfig", description="组态表")
public class StcConfig extends DataEntity<StcConfig> {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "组态id")
	private Long id;

	@Column(name = "config_name")
	@ApiModelProperty(value = "组态名称")
	private String configName;

	@Column(name = "config_des")
	@ApiModelProperty(value = "组态描述")
	private String configDes;

	@Column(name = "config_project")
	@ApiModelProperty(value = "所属项目")
	private Long configProject;

	@Column(name = "config_system")
	@ApiModelProperty(value = "所属系统")
	private Long configSystem;

	@Column(name = "config_icon")
	@ApiModelProperty(value = "组态图标")
	private String configIcon;

	@Column(name = "config_type")
	@ApiModelProperty(value = "组态类型 10 PC 20 移动")
	private Integer configType;

	@Column(name = "config_status")
	@ApiModelProperty(value = "组态状态 11 正常 5 隐藏")
	private Integer configStatus;

	


}
