package com.github.yoma.core.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统菜单Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_menu")
@ApiModel(value = "BaseMenu", description = "系统菜单")
public class BaseMenu extends DataEntity<BaseMenu> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    private List<BaseMenu> children;

    public Boolean getHasChildren() {
        if (subCount == null) {
            return false;
        } else {
            return subCount > 0;
        }

    }

    public Boolean getLeaf() {
        if (subCount == null) {
            return true;
        } else {
            return subCount <= 0;
        }

    }

    public String getLabel() {
        return title;
    }

    private String componentName;

    @Column(name = "pid")
    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;

    @Column(name = "sub_count")
    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    @Column(name = "type")
    @ApiModelProperty(value = "菜单类型")
    private Long type;

    @Column(name = "title")
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @Column(name = "name")
    @ApiModelProperty(value = "组件名称")
    private String name;

    @Column(name = "component")
    @ApiModelProperty(value = "组件")
    private String component;

    @Column(name = "menu_sort")
    @ApiModelProperty(value = "排序")
    private Integer menuSort;

    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Column(name = "path")
    @ApiModelProperty(value = "链接地址")
    private String path;

    @Column(name = "i_frame")
    @ApiModelProperty(value = "是否外链")
    private Boolean iFrame;

    @Column(name = "cache")
    @ApiModelProperty(value = "缓存")
    private Boolean cache;

    @Column(name = "hidden")
    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;

    @Column(name = "permission")
    @ApiModelProperty(value = "权限")
    private String permission;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
