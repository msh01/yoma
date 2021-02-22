package com.github.yoma.core.dto;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@ApiModel("数据字典")
public class BaseDictQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "范围筛选 创建日期开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建日期结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "范围筛选 更新时间开始")
    private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 更新时间结束")
    private LocalDateTime endUpdateTime;

}
