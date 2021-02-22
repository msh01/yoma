package com.github.yoma.core.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_user")
@ApiModel(value = "BaseUser", description = "系统用户")
public class BaseUser extends DataEntity<BaseUser> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "userType")
    private Integer userType;
    @ApiModelProperty(value = "companyId")
    private Long companyId;

    @JsonIgnore
    private Boolean isAdmin = false;

    @Column(name = "dept_id")
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @Column(name = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(name = "nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Column(name = "gender")
    @ApiModelProperty(value = "性别")
    private String gender;

    @Column(name = "phone")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column(name = "avatar_name")
    @ApiModelProperty(value = "头像地址")
    private String avatarName;

    @Column(name = "avatar_path")
    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;

    @Column(name = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    // @Column(name = "is_admin")
    // @ApiModelProperty(value = "是否为admin账号")
    // private Integer isAdmin;

    @Column(name = "enabled")
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Boolean enabled;

    @Column(name = "pwd_reset_time")
    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // @ManyToMany
    @ApiModelProperty(value = "用户角色")
    // @JoinTable(name = "sys_users_roles",
    // joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
    // inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private List<BaseRole> roles;

    // @ManyToMany
    @ApiModelProperty(value = "用户岗位")
    // @JoinTable(name = "sys_users_jobs",
    // joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
    // inverseJoinColumns = {@JoinColumn(name = "job_id",referencedColumnName = "job_id")})
    private List<BaseJob> jobs;

    // @OneToOne
    // @JoinColumn(name = "dept_id")
    @ApiModelProperty(value = "用户部门")
    private BaseDept dept;

}
