package com.github.yoma.order.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.github.yoma.order.domain.BetonCheckingDetail;
import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对账结果 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@ApiModel("对账结果表")
public class BetonCheckingQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对账单编号(导出时在字符串前增加显示-搅拌站对账编号以B开头,客户对账编号以C开头)")
    private String checkingNo;
    @ApiModelProperty(value = "对账单id")
    private Long checkingId;

    @ApiModelProperty(value = "类型 0-搅拌站 1-客户")
    private Integer type1;
    @ApiModelProperty(value = "范围筛选 数据日期开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDataDate;

    @ApiModelProperty(value = "范围筛选 数据日期结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDataDate;
    @ApiModelProperty(value = "checkingDetails")
    private List<BetonCheckingDetail> checkingDetails;

    @ApiModelProperty(value = "检查是否存在未审核或审核失败数据  0-检查 1-不检查")
    private Integer type;
    @ApiModelProperty(value = "ifAgain 0是 1否 ")
    private Integer ifAgain;

    @ApiModelProperty(value = "累积对账次数")
    private Integer cumNum;

    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @ApiModelProperty(value = "范围筛选 数据开始日期开始")
    private LocalDate beginBeginDate;

    @ApiModelProperty(value = "范围筛选 数据开始日期结束")
    private LocalDate endBeginDate;

    @ApiModelProperty(value = "范围筛选 数据截止日期开始")
    private LocalDate beginEndDate;

    @ApiModelProperty(value = "范围筛选 数据截止日期结束")
    private LocalDate endEndDate;

    @ApiModelProperty(value = "搅拌站(或客户)核对状态 0-未核对 1-核对正确 2-数据错误 3-平台强制核对通过")
    private Integer financeType;

    @ApiModelProperty(value = "搅拌站核对时间")
    private LocalDateTime financeTime;

    @ApiModelProperty(value = "搅拌站审核人")
    private Long financeId;

    @ApiModelProperty(value = "平台核对状态 0-未核对 1-核对正确 2-数据错误")
    private Integer paasType;

    @ApiModelProperty(value = "范围筛选 平台核对时间开始")
    private LocalDateTime beginPaasTime;

    @ApiModelProperty(value = "范围筛选 平台核对时间结束")
    private LocalDateTime endPaasTime;

    @ApiModelProperty(value = "平台审核人")
    private Long paasId;

    @ApiModelProperty(value = "是否生效 0-未生效 1-待生效 2-已生效 99-删除")
    private Integer enableType;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime enableTime;

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
