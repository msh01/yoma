package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色菜单 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("角色菜单")
public class CoreRoleMenuQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "角色主键")
    private Long roleId;

    @ApiModelProperty(value = "权限主键")
    private Long menuId;

}
