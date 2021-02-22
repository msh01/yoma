package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 账户信息 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("账户信息表")
public class CoreAccountQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "登录名")
    private String accountLogin;

    @ApiModelProperty(value = "登录密码")
    private String accountPass;

    @ApiModelProperty(value = "绑定的角色类型")
    private Integer roleType;
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "项目id")
    private Long projectId;
    @ApiModelProperty(value = "c移动端切换角色时，需要更新此字段。对应account_project主键id")
    private Long currentAccountProject;

    @ApiModelProperty(value = "范围筛选 上次登录时间开始")
    private LocalDateTime beginAccountLoginLast;

    @ApiModelProperty(value = "范围筛选 上次登录时间结束")
    private LocalDateTime endAccountLoginLast;

    @ApiModelProperty(value = "11 系统 22 公司 33 平台")
    private Integer currentType;

    @ApiModelProperty(value = "关联 core_role 表 id")
    private Long currentRole;

    @ApiModelProperty(value = "用户手机")
    private String accountMobile;

    @ApiModelProperty(value = "用户状态")
    private Integer accountStatus;

}
