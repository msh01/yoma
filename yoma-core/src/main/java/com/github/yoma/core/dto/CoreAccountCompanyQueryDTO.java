package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 集团账户关系 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("集团账户关联表")
public class CoreAccountCompanyQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "集团/公司主键")
    private Long companyId;

    @ApiModelProperty(value = "账户主键")
    private Long accountId;

    @ApiModelProperty(value = "所属角色")
    private Long roleId;
    @ApiModelProperty(value = "systemRole = 0时 只返回core_role.role_target>0的 role.id所对应的记录; "
        + "systemRole默认为0 也就是不传的时候也只返回非系统设置角色相关账户")
    private Integer systemRole;

}
