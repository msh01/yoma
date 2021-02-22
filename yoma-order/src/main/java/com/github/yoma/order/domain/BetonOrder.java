package com.github.yoma.order.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.github.yoma.order.service.excel.converter.*;
import com.github.yoma.common.persistence.DataEntity;

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
public class BetonOrder extends DataEntity<BetonOrder> {

    private static final long serialVersionUID = 1L;
    @Id
    @ExcelIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "orderNo")
    @ExcelProperty(value = "运输单号", index = 0)
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "性状 1-混凝土 2-砂浆")
    private Integer trait;
    @ExcelIgnore
    @ApiModelProperty(value = "文件id，傳值用")
    private String fileId;

    @ExcelProperty(value = "运输单号", index = 1)
    @ApiModelProperty(value = "")
    private String orderType;

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
    @Column(name = "betonClientName")

    @ExcelIgnore
    @ApiModelProperty(value = "客户名称")
    private String betonClientName;
    @Column(name = "betonClientId")
    @ExcelProperty(value = "商砼客户ID", index = 7, converter = CustomerIdConverter.class)
    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @Column(name = "projectName")
    @ExcelIgnore
    @ApiModelProperty(value = "工程名称")
    private String projectName;
    @Column(name = "projectId")
    @ExcelProperty(value = "生产时间", index = 8, converter = ProjectIdConverter.class)
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

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

    @ExcelProperty(value = "  搅拌站", index = 36)
    @Column(name = "grossTon1")
    @ApiModelProperty(value = "毛重 搅拌站")
    private Double grossTon1;

    @Column(name = "tareTon1")
    @ExcelProperty(value = "  搅拌站", index = 37)
    @ApiModelProperty(value = "皮重 搅拌站")
    private Double tareTon1;

    @Column(name = "realityTon1")
    @ExcelProperty(value = "  搅拌站", index = 38)
    @ApiModelProperty(value = "净重 搅拌站")
    private Double realityTon1;
    @Column(name = "timeOut")
    @ExcelProperty(value = "是否超时", index = 64, converter = TimeoutStatusConverter.class)
    @ApiModelProperty(value = "超时状态 0-正常 1-超时")
    private Integer timeOut;

    @ExcelIgnore
    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;
    @ExcelIgnore
    @Column(name = "companyName")
    @ApiModelProperty(value = "搅拌站名称 关联 t_client_infor 的 companyName")
    private String companyName;
    @ExcelIgnore
    @Column(name = "orderNoERP")
    @ApiModelProperty(value = "ERP订单编号")
    private String orderNoERP;

    @ExcelIgnore
    @Column(name = "carNo2")
    @ApiModelProperty(value = "车牌号")
    private String carNo2;

    @Column(name = "parts")
    @ExcelProperty(value = "施工部位", index = 12)
    @ApiModelProperty(value = "施工部位")
    private String parts;
    @ExcelProperty(value = "施工部位", index = 19)
    @Column(name = "carNo1")
    @ApiModelProperty(value = "车号（车辆在搅拌站的编号）")
    private String carNo1;

    @ExcelIgnore
    @Column(name = "permeate")
    @ApiModelProperty(value = "渗透等级")
    private String permeate;

    @ExcelIgnore
    @Column(name = "grossTon2")
    @ApiModelProperty(value = "毛重 施工方")
    private Double grossTon2;
    @ExcelIgnore
    @Column(name = "tareTon2")
    @ApiModelProperty(value = "皮重 施工方")
    private Double tareTon2;
    @ExcelIgnore
    @Column(name = "realityTon2")
    @ApiModelProperty(value = "净重 施工方")
    private Double realityTon2;
    @ExcelIgnore
    @Column(name = "cubicNum2")
    @ApiModelProperty(value = "方量 施工方")
    private Double cubicNum2;
    @ExcelIgnore
    @Column(name = "cubicNum")
    @ApiModelProperty(value = "方量（实际执行，财务核对时确认该方量）")
    private Double cubicNum;
    @ExcelIgnore
    @Column(name = "waybillUrl")
    @ApiModelProperty(value = "运输单据")
    private String waybillUrl;
    @ExcelIgnore
    @Column(name = "source")
    @ApiModelProperty(value = "数据来源 1-搅拌站录入 2-ERP系统对接 3-Excel导入 4-订单 5-平台财务录入")
    private Integer source;
    @ExcelIgnore
    @Column(name = "state")
    @ApiModelProperty(value = "订单状态 0-进行中 1-已完成 2-退回 99-删除")
    private Integer state;
    @ExcelIgnore
    @Column(name = "overTime")
    @ApiModelProperty(value = "订单完成时间")
    private LocalDateTime overTime;

    @ExcelIgnore
    @Column(name = "inTime")
    @ApiModelProperty(value = "进场时间 进入施工现场时间")
    private LocalDateTime inTime;
    @ExcelIgnore
    @Column(name = "outTime")
    @ApiModelProperty(value = "离场时间 离开施工现场时间")
    private LocalDateTime outTime;
    @ExcelIgnore
    @Column(name = "financeType")
    @ApiModelProperty(value = "财务核对状态 0-未核对 1-核对通过 2-核对不通过")
    private Integer financeType;
    @ExcelIgnore
    @Column(name = "financeId")
    @ApiModelProperty(value = "财务核对人ID")
    private Long financeId;
    @ExcelIgnore
    @Column(name = "financeName")
    @ApiModelProperty(value = "财务核对人姓名")
    private String financeName;
    @ExcelIgnore
    @Column(name = "financeTime")
    @ApiModelProperty(value = "财务核对时间")
    private LocalDateTime financeTime;
    @ExcelIgnore
    @Column(name = "financeRemark")
    @ApiModelProperty(value = "财务备注")
    private String financeRemark;
    @ExcelIgnore
    @Column(name = "tallyType")
    @ApiModelProperty(value = "入账状态 0-未入账 1-已入账（财务生成对账单后置为入账状态）")
    private Integer tallyType;
    @ExcelIgnore
    @Column(name = "tallyTime")
    @ApiModelProperty(value = "入账时间")
    private LocalDateTime tallyTime;
    @ExcelIgnore
    @Column(name = "createId")
    @ApiModelProperty(value = "创建人ID")
    private Long createId;
    @ExcelIgnore
    @Column(name = "createName")
    @ApiModelProperty(value = "创建人姓名")
    private String createName;
    @ExcelIgnore
    @Column(name = "createTime")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ExcelIgnore
    @Column(name = "updateTime")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
