package com.github.yoma.core.dto;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@ApiModel("系统用户")
public class BaseUserQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否为admin账号")
    private Integer isAdmin;

    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Long enabled;

    @ApiModelProperty(value = "范围筛选 修改密码的时间开始")
    private LocalDateTime beginPwdResetTime;

    @ApiModelProperty(value = "范围筛选 修改密码的时间结束")
    private LocalDateTime endPwdResetTime;

    @ApiModelProperty(value = "范围筛选 创建日期开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建日期结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "范围筛选 更新时间开始")
    private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 更新时间结束")
    private LocalDateTime endUpdateTime;

}
