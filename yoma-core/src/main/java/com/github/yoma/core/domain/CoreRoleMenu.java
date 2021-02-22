package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色菜单Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_role_menu")
@ApiModel(value = "CoreRoleMenu", description = "角色菜单")
public class CoreRoleMenu extends DataEntity<CoreRoleMenu> {

    private static final long serialVersionUID = 1L;
    @Column(name = "role_id")
    @ApiModelProperty(value = "角色主键")
    private Long roleId;

    @Column(name = "menu_id")
    @ApiModelProperty(value = "权限主键")
    private Long menuId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键id")
    private Long id;

}
