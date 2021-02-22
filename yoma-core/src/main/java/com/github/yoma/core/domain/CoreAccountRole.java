package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台账号-角色关系Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_account_role")
@ApiModel(value = "CoreAccountRole", description = "平台账户-角色关系表")
public class CoreAccountRole extends DataEntity<CoreAccountRole> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "平台账户角色表主键")
    private Long id;

    @Column(name = "account_id")
    @ApiModelProperty(value = "账户主键")
    private Long accountId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @Column(name = "role_id")
    @ApiModelProperty(value = "角色主键")
    private Long roleId;


}
