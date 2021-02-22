package com.github.yoma.order.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 砼标号价格清单 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@Data
@ApiModel("砼标号价格清单表")
public class BetonPriceDetailQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对账编号（与对账结果表中 对账编号 相同）")
    private String checkingNo;

    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @ApiModelProperty(value = "类型 0-搅拌站 1-客户")
    private Integer type1;

    @ApiModelProperty(value = "砼强度")
    private String conStrength;

}
