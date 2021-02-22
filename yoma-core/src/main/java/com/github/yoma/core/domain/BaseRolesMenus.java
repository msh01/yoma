package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色菜单关联Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_roles_menus")
@ApiModel(value = "BaseRolesMenus", description = "角色菜单关联")
public class BaseRolesMenus extends DataEntity<BaseRolesMenus> {

    private static final long serialVersionUID = 1L;
    @Column(name = "menu_id")
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @Column(name = "role_id")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键id")
    private Long id;

}
