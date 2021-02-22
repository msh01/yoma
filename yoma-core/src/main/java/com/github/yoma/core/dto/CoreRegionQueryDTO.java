package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 行政区域 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("行政区域表")
public class CoreRegionQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级主键")
    private String parentId;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "区域类型")
    private Integer regionType;

    @ApiModelProperty(value = "级联id")
    private String cascadeIds;

}
