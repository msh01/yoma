package com.github.yoma.order.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商砼收付款累计Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-17
 */
@Data
@Table(name = "t_beton_money_total")
@ApiModel(value = "BetonMoneyTotal", description = "商砼销售收付款累计资金表")
public class BetonMoneyTotal extends DataEntity<BetonMoneyTotal> {

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

    @Column(name = "receivable")
    @ApiModelProperty(value = "应收金额(金额只累加)")
    private Double receivable;

    @Column(name = "backReceivable")
    @ApiModelProperty(value = "已回款金额(金额只累加)")
    private Double backReceivable;

    @Column(name = "betonReceivable")
    @ApiModelProperty(value = "应付混凝土站金额(金额只累加)")
    private Double betonReceivable;

    @Column(name = "betonBackReceivable")
    @ApiModelProperty(value = "已付混凝土站金额(金额只累加)")
    private Double betonBackReceivable;

    @Column(name = "carReceivable")
    @ApiModelProperty(value = "应付车辆金额(金额只累加)")
    private Double carReceivable;

    @Column(name = "carBackReceivable")
    @ApiModelProperty(value = "已付车辆金额(金额只累加)")
    private Double carBackReceivable;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
