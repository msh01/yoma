package com.github.yoma.order.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;


/**
 * 商砼运输订单 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-11-19
 */
@Data
@ApiModel("商砼运输订单明细表")
public class BetonOrderQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "订单编号")
	private String orderNo;


	@ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
	private Long clientId;


	@ApiModelProperty(value = "搅拌站名称 关联 t_client_infor 的 companyName")
	private String companyName;


	@ApiModelProperty(value = "ERP订单编号")
	private String orderNoERP;


	@ApiModelProperty(value = "范围筛选 生产时间开始")
	private LocalDateTime beginManufactureTime;

    @ApiModelProperty(value = "范围筛选 生产时间结束")
	private LocalDateTime endManufactureTime;


	@ApiModelProperty(value = "车号（车辆在搅拌站的编号）")
	private String carNo1;


	@ApiModelProperty(value = "车牌号")
	private String carNo2;


	@ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
	private Long betonClientId;


	@ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
	private Long projectId;


	@ApiModelProperty(value = "客户名称")
	private String betonClientName;


	@ApiModelProperty(value = "工程名称")
	private String projectName;


	@ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
	private Integer castType;


	@ApiModelProperty(value = "施工部位")
	private String parts;



	@ApiModelProperty(value = "坍落度")
	private String slump;


	@ApiModelProperty(value = "渗透等级")
	private String permeate;


	@ApiModelProperty(value = "车次")
	private Integer carNum;












	@ApiModelProperty(value = "数据来源 1-搅拌站录入 2-ERP系统对接 3-Excel导入 4-订单 5-平台财务录入")
	private Integer source;


	@ApiModelProperty(value = "订单状态 0-进行中 1-已完成 2-退回 99-删除")
	private Integer state;


	@ApiModelProperty(value = "范围筛选 订单完成时间开始")
	private LocalDateTime beginOverTime;

    @ApiModelProperty(value = "范围筛选 订单完成时间结束")
	private LocalDateTime endOverTime;


	@ApiModelProperty(value = "超时状态 0-正常 1-超时")
	private Integer timeOut;


	@ApiModelProperty(value = "范围筛选 进场时间 进入施工现场时间开始")
	private LocalDateTime beginInTime;

    @ApiModelProperty(value = "范围筛选 进场时间 进入施工现场时间结束")
	private LocalDateTime endInTime;


	@ApiModelProperty(value = "范围筛选 离场时间 离开施工现场时间开始")
	private LocalDateTime beginOutTime;

    @ApiModelProperty(value = "范围筛选 离场时间 离开施工现场时间结束")
	private LocalDateTime endOutTime;


	@ApiModelProperty(value = "财务核对状态 0-未核对 1-核对通过 2-核对不通过")
	private Integer financeType;


	@ApiModelProperty(value = "财务核对人ID")
	private Long financeId;


	@ApiModelProperty(value = "财务核对人姓名")
	private String financeName;


	@ApiModelProperty(value = "范围筛选 财务核对时间开始")
	private LocalDateTime beginFinanceTime;

    @ApiModelProperty(value = "范围筛选 财务核对时间结束")
	private LocalDateTime endFinanceTime;



	@ApiModelProperty(value = "入账状态 0-未入账 1-已入账（财务生成对账单后置为入账状态）")
	private Integer tallyType;


	@ApiModelProperty(value = "范围筛选 入账时间开始")
	private LocalDateTime beginTallyTime;

    @ApiModelProperty(value = "范围筛选 入账时间结束")
	private LocalDateTime endTallyTime;


	@ApiModelProperty(value = "创建人ID")
	private Long createId;


	@ApiModelProperty(value = "创建人姓名")
	private String createName;


	@ApiModelProperty(value = "范围筛选 创建时间开始")
	private LocalDateTime beginCreateTime;

    @ApiModelProperty(value = "范围筛选 创建时间结束")
	private LocalDateTime endCreateTime;


	@ApiModelProperty(value = "范围筛选 修改时间开始")
	private LocalDateTime beginUpdateTime;

    @ApiModelProperty(value = "范围筛选 修改时间结束")
	private LocalDateTime endUpdateTime;






}
