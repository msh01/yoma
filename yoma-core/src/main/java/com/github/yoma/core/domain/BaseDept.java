package com.github.yoma.core.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_dept")
@ApiModel(value = "BaseDept", description = "部门")
public class BaseDept extends DataEntity<BaseDept> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

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
        return name;
    }

    @Column(name = "pid")
    @ApiModelProperty(value = "上级部门")
    private Long pid;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BaseDept> children;

    @Column(name = "sub_count")
    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    @Column(name = "name")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(name = "dept_sort")
    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    @Column(name = "enabled")
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
