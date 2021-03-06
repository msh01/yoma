package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色菜单关联 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@ApiModel("角色菜单关联")
public class BaseRolesMenusQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

}
