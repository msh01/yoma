package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单和操作按钮Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_menu")
@ApiModel(value = "CoreMenu", description = "菜单和操作按钮")
public class CoreMenu extends DataEntity<CoreMenu> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "i_frame")
    @ApiModelProperty(value = "是否外链")
    private Integer iFrame;

    @Column(name = "name")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @Column(name = "component")
    @ApiModelProperty(value = "组件")
    private String component;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    @Column(name = "sort")
    @ApiModelProperty(value = "排序")
    private Long sort;

    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Column(name = "path")
    @ApiModelProperty(value = "链接地址")
    private String path;

    @Column(name = "cache")
    @ApiModelProperty(value = "cache")
    private Integer cache;

    @Column(name = "hidden")
    @ApiModelProperty(value = "hidden")
    private Integer hidden;

    @Column(name = "component_name")
    @ApiModelProperty(value = "component_name")
    private String componentName;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "permission")
    @ApiModelProperty(value = "permission")
    private String permission;

    @Column(name = "type")
    @ApiModelProperty(value = "type")
    private Long type;

    @Column(name = "cascade_ids")
    @ApiModelProperty(value = "级联ids")
    private String cascadeIds;

    @Column(name = "level")
    @ApiModelProperty(value = "层级")
    private String level;

}
