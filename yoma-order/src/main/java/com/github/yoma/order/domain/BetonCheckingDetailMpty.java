package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对账补充明细Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@Table(name = "t_beton_checking_detail_mpty" )
@ApiModel(value = "BetonCheckingDetailMpty", description="补充项明细表")
public class BetonCheckingDetailMpty extends DataEntity<BetonCheckingDetailMpty> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "checkingNo")
	@ApiModelProperty(value = "对账编号（与对账结果表中 对账编号 相同）")
	private String checkingNo;

	@Column(name = "type1")
	@ApiModelProperty(value = "类型 0-搅拌站 1-客户")
	private Integer type1;

	@Column(name = "dataDate")
	@ApiModelProperty(value = "数据日期")
	private LocalDate dataDate;

	@Column(name = "dataType")
	@ApiModelProperty(value = "数据类型 1-扣方 2-调差价 3-纠错")
	private Integer dataType;

	@Column(name = "conStrength")
	@ApiModelProperty(value = "砼标号")
	private String conStrength;

	@Column(name = "cubicNum1")
	@ApiModelProperty(value = "小票方量")
	private Double cubicNum1;

	@Column(name = "cubicNum")
	@ApiModelProperty(value = "结算方量")
	private Double cubicNum;

	@Column(name = "unitPrice")
	@ApiModelProperty(value = "含税单价")
	private Double unitPrice;

	@Column(name = "unitSellingPrice")
	@ApiModelProperty(value = "不含税单价")
	private Double unitSellingPrice;

	@Column(name = "unitPriceSum")
	@ApiModelProperty(value = "含税金额")
	private Double unitPriceSum;

	@Column(name = "unitSellingPriceSum")
	@ApiModelProperty(value = "不含税金额")
	private Double unitSellingPriceSum;

	@Column(name = "officialPrice")
	@ApiModelProperty(value = "信息价(含税)")
	private Double officialPrice;

	@Column(name = "officialSellingPrice")
	@ApiModelProperty(value = "信息价(不含税)")
	private Double officialSellingPrice;

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
