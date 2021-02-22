package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色部门关联Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_roles_depts")
@ApiModel(value = "BaseRolesDepts", description = "角色部门关联")
public class BaseRolesDepts extends DataEntity<BaseRolesDepts> {

    private static final long serialVersionUID = 1L;
    @Column(name = "role_id")
    @ApiModelProperty(value = "role_id")
    private Long roleId;

    @Column(name = "dept_id")
    @ApiModelProperty(value = "dept_id")
    private Long deptId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Long id;

}
