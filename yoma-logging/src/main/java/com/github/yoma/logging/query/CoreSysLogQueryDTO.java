package com.github.yoma.logging.query;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 系统日志 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-11-02
 */
@Data
@ApiModel("core_sys_log")
public class CoreSysLogQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "范围筛选 操作时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 操作时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "操作对象（大多数对应的是表名）")
	private String target;


	@ApiModelProperty(value = "执行动作（类型太多，所以干脆存入文本）")
	private String operation;


	@ApiModelProperty(value = "描述")
	private String description;


	@ApiModelProperty(value = "异常细节")
	private String exceptionDetail;


	@ApiModelProperty(value = "日志执行结果（info成功执行,exception代表出现异常）")
	private String logCode;


	@ApiModelProperty(value = "执行方法（目前只限定为控制层）")
	private String method;



	@ApiModelProperty(value = "请求ip")
	private String requestIp;



	@ApiModelProperty(value = "操作用户账号姓名（冗余）")
	private String accountName;


	@ApiModelProperty(value = "请求ip来源地址")
	private String address;


	@ApiModelProperty(value = "浏览器")
	private String browser;






}
