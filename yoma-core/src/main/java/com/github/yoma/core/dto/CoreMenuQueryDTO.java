package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单和操作按钮 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("菜单和操作按钮")
public class CoreMenuQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否外链")
    private Integer iFrame;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "hidden")
    private Integer hidden;

    @ApiModelProperty(value = "permission")
    private String permission;

    @ApiModelProperty(value = "type")
    private Long type;

    @ApiModelProperty(value = "级联ids")
    private String cascadeIds;

    @ApiModelProperty(value = "层级")
    private String level;

}
