package com.github.yoma.order.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搅拌站登录用户Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-03
 */
@Data
@Table(name = "t_client_infor")
@ApiModel(value = "ClientInfor", description = "记录客户相关的用户信息")
public class ClientInfor extends DataEntity<ClientInfor> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "loginAppId")
    @ApiModelProperty(value = "登录账号ID 关联 T_LOGIN_APP 的ID")
    private Long loginAppId;

    @Column(name = "accType")
    @ApiModelProperty(value = "账号性质 1-企业 2-个人 3-平台料场")
    private Integer accType;

    @Column(name = "companyName")
    @ApiModelProperty(value = "企业名称或个人姓名")
    private String companyName;

    @Column(name = "contact")
    @ApiModelProperty(value = "联系人姓名")
    private String contact;

    @Column(name = "contactTel")
    @ApiModelProperty(value = "联系人电话")
    private String contactTel;

    @Column(name = "contactJob")
    @ApiModelProperty(value = "联系人职务")
    private String contactJob;

    @Column(name = "addr")
    @ApiModelProperty(value = "地址")
    private String addr;

    @Column(name = "ploginAppId")
    @ApiModelProperty(value = "主账号登录ID 关联 T_LOGIN_APP 的ID")
    private Long ploginAppId;

    @Column(name = "role")
    @ApiModelProperty(value = "账号权限 0-无权限 1-有权限 按顺序位表示权限类型 0-主账号 1-报价 2-验收 3-核对 4-审核报价 5-商砼销售 6-超时审核")
    private String role;

    @Column(name = "sysRole")
    @ApiModelProperty(value = "账号系统权限 0-无权限 1-有权限 按顺序位表示权限类型 0-普通权限 1-派单权限 2-短盘收货权限 3-短盘发货权限 4-立即生效 5-短盘(货场)")
    private String sysRole;

    @Column(name = "salesmanId")
    @ApiModelProperty(value = "业务员ID 关联 T_LOGIN_MANAGE 的 ID")
    private Long salesmanId;

    @Column(name = "salesmanName")
    @ApiModelProperty(value = "业务员姓名")
    private String salesmanName;

    @Column(name = "createTime")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Column(name = "updTime")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updTime;

}
