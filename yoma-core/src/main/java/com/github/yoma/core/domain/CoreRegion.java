package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 行政区域Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_region")
@ApiModel(value = "CoreRegion", description = "行政区域表")
public class CoreRegion extends DataEntity<CoreRegion> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    @ApiModelProperty(value = "区域主键")
    private String regionId;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "父级主键")
    private String parentId;

    @Column(name = "region_name")
    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @Column(name = "region_type")
    @ApiModelProperty(value = "区域类型")
    private Integer regionType;

    @Column(name = "agency_id")
    @ApiModelProperty(value = "备用字段")
    private String agencyId;

    @Column(name = "cascade_ids")
    @ApiModelProperty(value = "级联id")
    private String cascadeIds;

}
