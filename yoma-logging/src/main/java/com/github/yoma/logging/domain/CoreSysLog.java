package com.github.yoma.logging.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统日志Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-11-02
 */
@Data
@Table(name = "core_sys_log" )
@ApiModel(value = "CoreSysLog", description="core_sys_log")
public class CoreSysLog extends DataEntity<CoreSysLog> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键id")
	private Long id;


	@Column(name = "create_time")
	@ApiModelProperty(value = "操作时间")
	private LocalDateTime createTime;

	@Column(name = "target")
	@ApiModelProperty(value = "操作对象（大多数对应的是表名）")
	private String target;

	@Column(name = "operation")
	@ApiModelProperty(value = "执行动作（类型太多，所以干脆存入文本）")
	private String operation;

	@Column(name = "description")
	@ApiModelProperty(value = "描述")
	private String description;

	@Column(name = "exception_detail")
	@ApiModelProperty(value = "异常细节")
	private String exceptionDetail;

	@Column(name = "log_code")
	@ApiModelProperty(value = "日志执行结果（info成功执行,exception代表出现异常）")
	private String logCode;

	@Column(name = "method")
	@ApiModelProperty(value = "执行方法（目前只限定为控制层）")
	private String method;

	@Column(name = "params")
	@ApiModelProperty(value = "参数")
	private String params;

	@Column(name = "request_ip")
	@ApiModelProperty(value = "请求ip")
	private String requestIp;

	@Column(name = "time")
	@ApiModelProperty(value = "请求耗时")
	private Long time;

	@Column(name = "account_name")
	@ApiModelProperty(value = "操作用户账号姓名（冗余）")
	private String accountName;

	@Column(name = "address")
	@ApiModelProperty(value = "请求ip来源地址")
	private String address;

	@Column(name = "browser")
	@ApiModelProperty(value = "浏览器")
	private String browser;




}
