package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色信息 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("角色信息表")
public class CoreRoleQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色类型 11平台 22公司 33项目")
    private Integer roleType;

    @ApiModelProperty(value = "公司/项目角色关联到对应的id 平台角色设为0")
    private Long roleTarget;
    @ApiModelProperty(value = "accountIdForSystem")
    private Long accountIdForSystem;
    @ApiModelProperty(value = "accountIdForCompany")
    private Long accountIdForCompany;
    @ApiModelProperty(value = "accountIdForProject")
    private Long accountIdForProject;

}
