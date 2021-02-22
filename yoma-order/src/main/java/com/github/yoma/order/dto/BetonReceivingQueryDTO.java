package com.github.yoma.order.dto;

import java.time.LocalDateTime;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商砼销售收款明细 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-09
 */
@Data
@ApiModel("商砼销售收付款明细表")
public class BetonReceivingQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @ApiModelProperty(value = "收付款类型 1-回款（客户） 2-付款（搅拌站） 3-付款（车辆）")
    private Integer paymentType;

    @ApiModelProperty(value = "付款方式 1-手动付款 2-系统自动付款")
    private Integer payType;

    @ApiModelProperty(value = "银行流水号")
    private String tradeNo;

    @ApiModelProperty(value = "收款单位名称")
    private String receivingUnit;

    @ApiModelProperty(value = "收款银行(总行名称)")
    private String receivingBank;

    @ApiModelProperty(value = "收款卡号")
    private String receivingCard;

    @ApiModelProperty(value = "付款单位名称")
    private String paymentUnit;

    @ApiModelProperty(value = "付款银行(总行名称)")
    private String paymentBank;

    @ApiModelProperty(value = "付款卡号")
    private String paymentCard;

    @ApiModelProperty(value = "范围筛选 付款时间开始")
    private LocalDateTime beginPayTime;

    @ApiModelProperty(value = "范围筛选 付款时间结束")
    private LocalDateTime endPayTime;

    @ApiModelProperty(value = "范围筛选 付款金额开始")
    private Double beginPaymentAmount;

    @ApiModelProperty(value = "范围筛选 付款金额结束")
    private Double endPaymentAmount;

    @ApiModelProperty(value = "范围筛选 创建时间开始")
    private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "创建人ID 0表示自动生成")
    private Long createId;

    @ApiModelProperty(value = "范围筛选 修改时间开始")
    private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 修改时间结束")
    private LocalDateTime endUpdateTime;

}
