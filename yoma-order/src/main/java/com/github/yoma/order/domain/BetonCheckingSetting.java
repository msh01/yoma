package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.order.businessenum.RateTypesEnum;
import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对账周期Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-11-30
 */
@Data
@Table(name = "t_beton_checking_setting")
@ApiModel(value = "BetonCheckingSetting", description = "对账周期表")
public class BetonCheckingSetting extends DataEntity<BetonCheckingSetting> {

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

    @ApiModelProperty(value = "工程名称")
    private String projectName;
    @ApiModelProperty(value = "客户名称")
    private String clientName;

    @Column(name = "projectId")
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @Column(name = "type1")
    @ApiModelProperty(value = "类型 0-搅拌站 1-客户")
    private Integer type1;

    @Column(name = "createType")
    @ApiModelProperty(value = "账单生成方式 0-手动 1-自动")
    private Integer createType;

    @Column(name = "createPeriod")
    @ApiModelProperty(value = "对账单生成周期 1-日 2-月 3-年")
    private Integer createPeriod;

    @Column(name = "compType")
    @ApiModelProperty(value = "小方补助状态 0-无补助 1-有补助, type=1 时 有效")
    private Integer compType;

    @Column(name = "minCubicNum")
    @ApiModelProperty(value = "最小方量 结算运费时使用")
    private Double minCubicNum;

    @Column(name = "minCubicNumPrice")
    @ApiModelProperty(value = "每方补助金额 结算运费时使用")
    private Double minCubicNumPrice;

    @Column(name = "rateType")
    @ApiModelProperty(value = "容重标准 0-固定值 1-实际值")
    private Integer rateType;

    public String getRateTypeText() {
        return RateTypesEnum.getEnum(this.getRateType()).text;
    }

    @Column(name = "rateHnt")
    @ApiModelProperty(value = "混凝土容重")
    private Integer rateHnt;

    @Column(name = "rateSj")
    @ApiModelProperty(value = "砂浆容重")
    private Integer rateSj;

    @Column(name = "priceMakupType")
    @ApiModelProperty(value = "加价状态 0-加价 1-不加价")
    private Integer priceMakupType;

    @Column(name = "templateUrl")
    @ApiModelProperty(value = "对账单模板URL")
    private String templateUrl;

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
