package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 砼标号价格清单Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-04
 */
@Data
@Table(name = "t_beton_price_detail")
@ApiModel(value = "BetonPriceDetail", description = "砼标号价格清单表")
public class BetonPriceDetail extends DataEntity<BetonPriceDetail> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "checkingNo")
    @ApiModelProperty(value = "对账编号（与对账结果表中 对账编号 相同）")
    private String checkingNo;

    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @Column(name = "betonClientId")
    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @Column(name = "projectId")
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @Column(name = "type1")
    @ApiModelProperty(value = "类型 0-搅拌站 1-客户")
    private Integer type1;

    @Column(name = "conStrength")
    @ApiModelProperty(value = "砼强度")
    private String conStrength;

    @Column(name = "unitPrice")
    @ApiModelProperty(value = "含税单价")
    private Double unitPrice;

    @Column(name = "unitSellingPrice")
    @ApiModelProperty(value = "不含税单价")
    private Double unitSellingPrice;

    @Column(name = "batch")
    @ApiModelProperty(value = "对账批次号")
    private String batch;

}
