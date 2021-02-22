package com.github.yoma.core.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典详情Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@Table(name = "base_dict_detail")
@ApiModel(value = "BaseDictDetail", description = "数据字典详情")
public class BaseDictDetail extends DataEntity<BaseDictDetail> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "dict_id")
    @ApiModelProperty(value = "字典id")
    private Long dictId;

    @Column(name = "label")
    @ApiModelProperty(value = "字典标签")
    private String label;

    @Column(name = "value")
    @ApiModelProperty(value = "字典值")
    private String value;

    @Column(name = "dict_sort")
    @ApiModelProperty(value = "排序")
    private Integer dictSort;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
