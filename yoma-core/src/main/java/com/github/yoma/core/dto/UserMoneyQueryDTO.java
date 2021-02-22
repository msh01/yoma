package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 账户资金 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-12-21
 */
@Data
@ApiModel("账户资金表")
public class UserMoneyQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户类型 1-搅拌站 2-车辆")
	private Integer userType;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "范围筛选 创建时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
	private LocalDateTime endCreateTime;

	@ApiModelProperty(value = "范围筛选 修改时间开始")
	private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 修改时间结束")
	private LocalDateTime endUpdateTime;

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
