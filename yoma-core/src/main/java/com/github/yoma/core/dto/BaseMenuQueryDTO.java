package com.github.yoma.core.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统菜单 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@ApiModel("系统菜单")
public class BaseMenuQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;
    @ApiModelProperty(value = "上级菜单ID")
    private Integer pidIsNull;

    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    @ApiModelProperty(value = "菜单类型")
    private Long type;
    @ApiModelProperty(value = "菜单类型")
    private Long typeNot;
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    Set<Long> roleIds;
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "组件名称")
    private String name;

    @ApiModelProperty(value = "是否外链")
    private Integer iFrame;

    @ApiModelProperty(value = "缓存")
    private Integer cache;

    @ApiModelProperty(value = "隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "范围筛选 创建日期开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建日期结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "范围筛选 更新时间开始")
    private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 更新时间结束")
    private LocalDateTime endUpdateTime;

}
