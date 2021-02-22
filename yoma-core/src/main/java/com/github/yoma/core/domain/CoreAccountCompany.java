package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 集团账户关系Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_account_company")
@ApiModel(value = "CoreAccountCompany", description = "集团账户关联表")
public class CoreAccountCompany extends DataEntity<CoreAccountCompany> {

    private static final long serialVersionUID = 1L;
    @Column(name = "company_id")
    @ApiModelProperty(value = "集团/公司主键")
    private Long companyId;

    @Column(name = "account_id")
    @ApiModelProperty(value = "账户主键")
    private Long accountId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @Column(name = "role_id")
    @ApiModelProperty(value = "所属角色")
    private Long roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "公司账户角色关系表主键")
    private Long id;


    // @Column(name = "account_name")
    // @ApiModelProperty(value = "账户名")
    // private String accountName;
    @ApiModelProperty(value = "手机号验证码")
    private String verificationCode;

    @Column(name = "account_login")
    @ApiModelProperty(value = "登录名")
    private String accountLogin;

    @Column(name = "account_pass")
    @ApiModelProperty(value = "登录密码")
    private String accountPass;

    @Column(name = "account_login_total")
    @ApiModelProperty(value = "登录次数")
    private Integer accountLoginTotal;

    @Column(name = "account_login_last")
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime accountLoginLast;

    @Column(name = "account_login_ip")
    @ApiModelProperty(value = "上次登录IP")
    private String accountLoginIp;

    @Column(name = "account_email")
    @ApiModelProperty(value = "用户邮箱")
    private String accountEmail;

    @Column(name = "account_mobile")
    @ApiModelProperty(value = "用户手机")
    private String accountMobile;

    @Column(name = "current_type")
    @ApiModelProperty(value = "11 系统 22 公司 33 平台")
    private Integer currentType;

    @Column(name = "current_role")
    @ApiModelProperty(value = "关联 core_role 表 id")
    private Long currentRole;

    @Column(name = "account_status")
    @ApiModelProperty(value = "用户状态")
    private Integer accountStatus;

}
