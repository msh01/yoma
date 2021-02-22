package com.github.yoma.order.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.github.yoma.order.service.excel.converter.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商砼运输订单Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-11-19
 */
@HeadRowHeight(20)
@ColumnWidth(18)
@ContentRowHeight(20)
@Data
@Table(name = "t_beton_order")
@ApiModel(value = "BetonOrder", description = "商砼运输订单明细表")
public class BetonOrderVo {

    private static final long serialVersionUID = 1L;

    // @Column(name = "orderNo")
    // @ExcelProperty(value = "运输单号", index = 0)
    // @ApiModelProperty(value = "订单编号")
    // private String orderNo;

    @Column(name = "orderNoERP")
    @ExcelProperty(value = "运输单号", index = 0)
    private String orderNoERP;

    @Column(name = "manufactureTime")
    @ExcelProperty(value = "生产时间", index = 2)
    @ApiModelProperty(value = "生产时间")
    private LocalDateTime manufactureTime;

    @ExcelProperty(value = "是否有效", index = 4, converter = EffectiveStatusConverter.class)
    @ApiModelProperty(value = "是否有效")
    private Integer effectiveStatus;

    @ExcelProperty(value = "是否审核", index = 5, converter = AuditStatusConverter.class)
    @ApiModelProperty(value = "是否审核")
    private Integer auditStatus;

    @ExcelProperty(value = "商砼客户ID", index = 7, converter = CustomerIdConverter.class)
    private Long betonClientId;
    // @ExcelProperty(value = "商砼客户名称", index = 7)
    // private String betonClientName;

    @ExcelProperty(value = "工程信息ID", index = 8, converter = ProjectIdConverter.class)
    private Long projectId;

    // @ExcelProperty(value = "工程信息名称", index = 8)
    // private String projectName;

    @ExcelProperty(value = "砼强度", index = 10)
    @Column(name = "conStrength")
    @ApiModelProperty(value = "砼强度")
    private String conStrength;

    @Column(name = "浇筑方式")
    @ExcelProperty(value = "砼强度", index = 11, converter = PourTypeConverter.class)
    @ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
    private Integer castType;

    @ExcelProperty(value = "累计车数", index = 20)
    @Column(name = "carNum")
    @ApiModelProperty(value = "车次")
    private Integer carNum;

    @ExcelProperty(value = "坍落度", index = 24)
    @Column(name = "slump")
    @ApiModelProperty(value = "坍落度")
    private String slump;

    @ExcelProperty(value = "出票方量", index = 28)
    @Column(name = "cubicNum1")
    @ApiModelProperty(value = "方量 搅拌站")
    private Double cubicNum1;

    @ExcelProperty(value = "毛重 搅拌站", index = 36)
    @Column(name = "grossTon1")
    @ApiModelProperty(value = "毛重 搅拌站")
    private Double grossTon1;

    @Column(name = "tareTon1")
    @ExcelProperty(value = "毛重 搅拌站", index = 37)
    @ApiModelProperty(value = "皮重 搅拌站")
    private Double tareTon1;

    @Column(name = "realityTon1")
    @ExcelProperty(value = "毛重 搅拌站", index = 38)
    @ApiModelProperty(value = "净重 搅拌站")
    private Double realityTon1;

    @Column(name = "parts")
    @ExcelProperty(value = "施工部位", index = 12)
    @ApiModelProperty(value = "施工部位")
    private String parts;

    @ExcelProperty(value = "施工部位", index = 19)
    @Column(name = "carNo1")
    @ApiModelProperty(value = "车号（车辆在搅拌站的编号）")
    private String carNo1;

    @Column(name = "timeOut")
    @ExcelProperty(value = "是否超时", index = 64, converter = TimeoutStatusConverter.class)
    @ApiModelProperty(value = "超时状态 0-正常 1-超时")
    private Integer timeOut;

}
