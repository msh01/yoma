package com.github.yoma.order.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对账结果Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@Table(name = "t_beton_checking")
@ApiModel(value = "BetonChecking", description = "对账结果表")
public class BetonChecking extends DataEntity<BetonChecking> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "checkingNo")
    @ApiModelProperty(value = "对账单编号(导出时在字符串前增加显示-搅拌站对账编号以B开头,客户对账编号以C开头)")
    private String checkingNo;
    private String stationName;
    private String projectERPNo;
    private String contractNo;
    private String projectName;
    private String clientName;
    private String betonClientName;

    private String monthSumRMB;
    private Double monthSum;
    private String totalSumRMB;
    private Double totalSum;
    private Long numSum;
    private Double amountSum;
    private Double amountSumTax;
    private Integer listSize;
    private Integer recycleListSize;

    @Column(name = "type1")
    @ApiModelProperty(value = "类型 0-搅拌站 1-客户")
    private Integer type1;
    @ApiModelProperty(value = "checkingDetails")
    private List<BetonCheckingDetail> checkingDetails;

    @Column(name = "cumNum")
    @ApiModelProperty(value = "累积对账次数")
    private Integer cumNum;

    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @Column(name = "betonClientId")
    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @Column(name = "projectId")
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @Column(name = "beginDate")
    @ApiModelProperty(value = "数据开始日期")
    private LocalDate beginDate;

    @Column(name = "endDate")
    @ApiModelProperty(value = "数据截止日期")
    private LocalDate endDate;

    @Column(name = "realityTon")
    @ApiModelProperty(value = "总净重")
    private Integer realityTon;

    @Column(name = "cubicNum")
    @ApiModelProperty(value = "总方量")
    private Double cubicNum;

    @Column(name = "mortarTon")
    @ApiModelProperty(value = "砂浆净重")
    private Integer mortarTon;

    @Column(name = "mortarCubicNum")
    @ApiModelProperty(value = "砂浆方量")
    private Double mortarCubicNum;

    @Column(name = "compMoney")
    @ApiModelProperty(value = "小方补助金额")
    private Double compMoney;

    @Column(name = "unitPriceSum")
    @ApiModelProperty(value = "含税金额")
    private Double unitPriceSum;

    @Column(name = "unitSellingPriceSum")
    @ApiModelProperty(value = "不含税金额")
    private Double unitSellingPriceSum;

    @Column(name = "detailNum")
    @ApiModelProperty(value = "对账明细数")
    private Integer detailNum;

    @Column(name = "mptyNum")
    @ApiModelProperty(value = "补充数据条数")
    private Integer mptyNum;

    @Column(name = "financeType")
    @ApiModelProperty(value = "搅拌站(或客户)核对状态 0-未核对 1-核对正确 2-数据错误 3-平台强制核对通过")
    private Integer financeType;

    @Column(name = "financeTime")
    @ApiModelProperty(value = "搅拌站核对时间")
    private LocalDateTime financeTime;

    @Column(name = "financeId")
    @ApiModelProperty(value = "搅拌站审核人")
    private Long financeId;

    @Column(name = "paasType")
    @ApiModelProperty(value = "平台核对状态 0-未核对 1-核对正确 2-数据错误")
    private Integer paasType;

    @Column(name = "paasTime")
    @ApiModelProperty(value = "平台核对时间")
    private LocalDateTime paasTime;

    @Column(name = "paasId")
    @ApiModelProperty(value = "平台审核人")
    private Long paasId;

    @Column(name = "enableType")
    @ApiModelProperty(value = "是否生效 0-未生效 1-待生效 2-已生效 99-删除")
    private Integer enableType;

    @Column(name = "enableTime")
    @ApiModelProperty(value = "生效时间")
    private LocalDateTime enableTime;

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
