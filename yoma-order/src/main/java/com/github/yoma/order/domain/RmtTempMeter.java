package com.github.yoma.order.domain;

import com.github.yoma.common.persistence.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 对账结果Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@ApiModel(value = "RmtTempMeter", description = "对账结果表")
public class RmtTempMeter extends DataEntity<RmtTempMeter> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "响应码(0000:成功)")
    private String Code;

    @ApiModelProperty(value = "响应描述")
    private String Message;

    @ApiModelProperty(value = "设备编号(14 位数字)")
    private Long DevId;

    @ApiModelProperty(value = "上传时间(yyyy-MM-dd HH:mm:ss)")
    private LocalDateTime UpTime;

    @Column(name = "beginDate")
    @ApiModelProperty(value = "上传类型(01 上电，02 自动，03 手\n" +
            "动，04 波动)")
    private Integer
            UpType;

    @Column(name = "endDate")
    @ApiModelProperty(value = "采集时间(yyyy-MM-dd HH:mm:ss)")
    private LocalDateTime CollectTime;

    @Column(name = "realityTon")
    @ApiModelProperty(value = "温度")
    private String Temperature;

    @Column(name = "cubicNum")
    @ApiModelProperty(value = "湿度")
    private String Humidity;


    @Column(name = "mortarTon")
    @ApiModelProperty(value = " 设备状态")
    private Integer DevState;

    @Column(name = "mortarCubicNum")
    @ApiModelProperty(value = "信号质量（0--31）")
    private Integer SignalQuality;

    @Column(name = "compMoney")
    @ApiModelProperty(value = "电池类型(01-弱点，02-强电)")
    private String CellType;

    @Column(name = "unitPriceSum")
    @ApiModelProperty(value = " 电池电压")
    private Double Vdc;

    @Column(name = "unitSellingPriceSum")
    @ApiModelProperty(value = "是否采集用电情况(0-不采集，1-采\n" +
            "集)")
    private String IsElectricity;

    @Column(name = "detailNum")
    @ApiModelProperty(value = " 累计电量")
    private String TotalElectricity;

    @Column(name = "mptyNum")
    @ApiModelProperty(value = " 瞬时功率")
    private String TranPower;

    @Column(name = "financeType")
    @ApiModelProperty(value = " 瞬时电压")
    private String TranVoltage;

    @Column(name = "financeTime")
    @ApiModelProperty(value = " 瞬时电流")
    private String TranCurrent;

    @Column(name = "financeId")
    @ApiModelProperty(value = "预留")
    private String Temp1;

    @Column(name = "paasType")
    @ApiModelProperty(value = "预留")
    private Integer Temp2;


}
