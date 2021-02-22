package com.github.yoma.core.dto;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-15
 */
@Data
@ApiModel("角色表")
public class BaseRoleQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "角色级别")
    private Long level;
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    @ApiModelProperty(value = "范围筛选 创建日期开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "创建人id", hidden = true)
    protected String createBy;
    @ApiModelProperty(value = "修改人id", hidden = true)
    protected String updateBy;
    @ApiModelProperty(value = "范围筛选 创建日期结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "范围筛选 更新时间开始")
    private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 更新时间结束")
    private LocalDateTime endUpdateTime;

}
