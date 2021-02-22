package com.github.yoma.core.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_role")
@ApiModel(value = "BaseRole", description = "角色表")
public class BaseRole extends DataEntity<BaseRole> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(name = "level")
    @ApiModelProperty(value = "角色级别")
    private Long level;

    @Column(name = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    List<BaseMenu> menus;
    @Column(name = "data_scope")
    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
