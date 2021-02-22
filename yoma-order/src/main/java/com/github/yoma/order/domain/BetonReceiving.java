package com.github.yoma.order.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商砼销售收款明细Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-09
 */
@Data
@Table(name = "t_beton_receiving")
@ApiModel(value = "BetonReceiving", description = "商砼销售收付款明细表")
public class BetonReceiving extends DataEntity<BetonReceiving> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @Column(name = "betonClientId")
    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @Column(name = "projectId")
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @Column(name = "paymentType")
    @ApiModelProperty(value = "收付款类型 1-回款（客户） 2-付款（搅拌站） 3-付款（车辆）")
    private Integer paymentType;

    @Column(name = "payType")
    @ApiModelProperty(value = "付款方式 1-手动付款 2-系统自动付款")
    private Integer payType;

    @Column(name = "tradeNo")
    @ApiModelProperty(value = "银行流水号")
    private String tradeNo;

    @Column(name = "receivingUnit")
    @ApiModelProperty(value = "收款单位名称")
    private String receivingUnit;

    @Column(name = "receivingBank")
    @ApiModelProperty(value = "收款银行(总行名称)")
    private String receivingBank;

    @Column(name = "receivingCard")
    @ApiModelProperty(value = "收款卡号")
    private String receivingCard;

    @Column(name = "paymentUnit")
    @ApiModelProperty(value = "付款单位名称")
    private String paymentUnit;

    @Column(name = "paymentBank")
    @ApiModelProperty(value = "付款银行(总行名称)")
    private String paymentBank;

    @Column(name = "paymentCard")
    @ApiModelProperty(value = "付款卡号")
    private String paymentCard;

    @Column(name = "payTime")
    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;

    @Column(name = "paymentAmount")
    @ApiModelProperty(value = "付款金额")
    private Double paymentAmount;

    @Column(name = "voucher")
    @ApiModelProperty(value = "转账凭证图片地址")
    private String voucher;

    @Column(name = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "createTime")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Column(name = "createId")
    @ApiModelProperty(value = "创建人ID 0表示自动生成")
    private Long createId;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
