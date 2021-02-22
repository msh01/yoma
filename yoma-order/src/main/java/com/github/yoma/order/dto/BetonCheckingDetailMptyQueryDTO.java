package com.github.yoma.order.dto;

import javax.persistence.*;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 对账补充明细 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@ApiModel("补充项明细表")
public class BetonCheckingDetailMptyQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "对账编号（与对账结果表中 对账编号 相同）")
	private String checkingNo;


	@ApiModelProperty(value = "类型 0-搅拌站 1-客户")
	private Integer type1;


	@ApiModelProperty(value = "范围筛选 数据日期开始")
	private LocalDate beginDataDate;

    @ApiModelProperty(value = "范围筛选 数据日期结束")
	private LocalDate endDataDate;


	@ApiModelProperty(value = "数据类型 1-扣方 2-调差价 3-纠错")
	private Integer dataType;


	@ApiModelProperty(value = "砼标号")
	private String conStrength;











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
