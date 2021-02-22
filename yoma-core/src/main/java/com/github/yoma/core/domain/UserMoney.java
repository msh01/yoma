package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账户资金Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@Table(name = "t_user_money" )
@ApiModel(value = "UserMoney", description="账户资金表")
public class UserMoney extends DataEntity<UserMoney> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "主键")
	private Long id;

	@Column(name = "userType")
	@ApiModelProperty(value = "用户类型 1-搅拌站 2-车辆")
	private Integer userType;

	@Column(name = "userId")
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@Column(name = "money")
	@ApiModelProperty(value = "账户剩余金额")
	private Double money;

	@Column(name = "applyMoney")
	@ApiModelProperty(value = "已申请提现金额")
	private Double applyMoney;

	@Column(name = "frozenMoney")
	@ApiModelProperty(value = "账户冻结金额")
	private Double frozenMoney;

	@Column(name = "createTime")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@Column(name = "updateTime")
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "车牌号码")
	private String cardNo;

	@ApiModelProperty(value = "车牌前缀")
	private String cardOrigin;

	@ApiModelProperty(value = "公司名称")
	private String companyName;

	@ApiModelProperty(value = "联系人姓名")
	private String contact;

	@ApiModelProperty(value = "业务员ID")
	private Long salesmanId;

	@ApiModelProperty(value = "业务员姓名")
	private String salesmanName;

}
