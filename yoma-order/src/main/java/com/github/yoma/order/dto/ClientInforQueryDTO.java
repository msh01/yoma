package com.github.yoma.order.dto;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搅拌站登录用户 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@Data
@ApiModel("记录客户相关的用户信息")
public class ClientInforQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录账号ID 关联 T_LOGIN_APP 的ID")
    private Long loginAppId;

    @ApiModelProperty(value = "账号性质 1-企业 2-个人 3-平台料场")
    private Integer accType;

    @ApiModelProperty(value = "企业名称或个人姓名")
    private String companyName;

    @ApiModelProperty(value = "联系人姓名")
    private String contact;

    @ApiModelProperty(value = "联系人电话")
    private String contactTel;

    @ApiModelProperty(value = "联系人职务")
    private String contactJob;

    @ApiModelProperty(value = "主账号登录ID 关联 T_LOGIN_APP 的ID")
    private Long ploginAppId;

    @ApiModelProperty(value = "账号权限 0-无权限 1-有权限 按顺序位表示权限类型 0-主账号 1-报价 2-验收 3-核对 4-审核报价 5-商砼销售 6-超时审核")
    private String role;

    @ApiModelProperty(value = "账号系统权限 0-无权限 1-有权限 按顺序位表示权限类型 0-普通权限 1-派单权限 2-短盘收货权限 3-短盘发货权限 4-立即生效 5-短盘(货场)")
    private String sysRole;

    @ApiModelProperty(value = "业务员ID 关联 T_LOGIN_MANAGE 的 ID")
    private Long salesmanId;

    @ApiModelProperty(value = "业务员姓名")
    private String salesmanName;

    @ApiModelProperty(value = "范围筛选 创建时间开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "范围筛选 修改时间开始")
    private LocalDateTime beginUpdTime;

    @ApiModelProperty(value = "范围筛选 修改时间结束")
    private LocalDateTime endUpdTime;

}
