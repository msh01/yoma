package com.github.yoma.order.dto;

import javax.persistence.*;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;


/**
 * 对账周期 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-11-30
 */
@Data
@ApiModel("对账周期表")
public class BetonCheckingSettingQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
	private Long clientId;


	@ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
	private Long betonClientId;


	@ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
	private Long projectId;


	@ApiModelProperty(value = "类型 0-搅拌站 1-客户")
	private Integer type1;


	@ApiModelProperty(value = "账单生成方式 0-手动 1-自动")
	private Integer createType;


	@ApiModelProperty(value = "对账单生成周期 1-日 2-月 3-年")
	private Integer createPeriod;


	@ApiModelProperty(value = "小方补助状态 0-无补助 1-有补助, type=1 时 有效")
	private Integer compType;


	@ApiModelProperty(value = "最小方量 结算运费时使用")
	private Double minCubicNum;


	@ApiModelProperty(value = "每方补助金额 结算运费时使用")
	private Double minCubicNumPrice;


	@ApiModelProperty(value = "容重标准 0-固定值 1-实际值")
	private Integer rateType;


	@ApiModelProperty(value = "混凝土容重")
	private Integer rateHnt;


	@ApiModelProperty(value = "砂浆容重")
	private Integer rateSj;


	@ApiModelProperty(value = "加价状态 0-加价 1-不加价")
	private Integer priceMakupType;



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
