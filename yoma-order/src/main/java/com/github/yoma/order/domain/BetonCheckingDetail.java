package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对账明细Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@Table(name = "t_beton_checking_detail")
@ApiModel(value = "BetonCheckingDetail", description = "对账明细表存每日的对账明细纬度:搅拌站-客户-项目-砼标号")
public class BetonCheckingDetail extends DataEntity<BetonCheckingDetail> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "状态 0-成功 1-失败")
    private Integer flag;
    @ApiModelProperty(value = "状态 0-成功 1-失败")
    private String msg;

    @Column(name = "dataDate")
    @ApiModelProperty(value = "数据日期")
    private LocalDate dataDate;

    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @Column(name = "betonClientId")
    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @Column(name = "projectId")
    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @Column(name = "parts")
    @ApiModelProperty(value = "施工部位")
    private String parts;

    @Column(name = "castType")
    @ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
    private Integer castType;

    @Column(name = "conStrength")
    @ApiModelProperty(value = "砼标号")
    private String conStrength;

    @Column(name = "realityTon1")
    @ApiModelProperty(value = "净重 搅拌站")
    private Integer realityTon1;

    @Column(name = "cubicNum1")
    @ApiModelProperty(value = "方量 搅拌站")
    private Double cubicNum1;

    @Column(name = "mortarTon1")
    @ApiModelProperty(value = "砂浆净重 搅拌站")
    private Integer mortarTon1;

    @Column(name = "mortarCubicNum1")
    @ApiModelProperty(value = "砂浆方量 搅拌站")
    private Double mortarCubicNum1;

    @Column(name = "realityTon2")
    @ApiModelProperty(value = "净重 客户")
    private Integer realityTon2;

    @Column(name = "cubicNum2")
    @ApiModelProperty(value = "方量 客户")
    private Double cubicNum2;

    @Column(name = "mortarTon2")
    @ApiModelProperty(value = "砂浆净重 客户")
    private Integer mortarTon2;

    @Column(name = "mortarCubicNum2")
    @ApiModelProperty(value = "砂浆方量 客户")
    private Double mortarCubicNum2;

    @Column(name = "realityTon")
    @ApiModelProperty(value = "净重 结算")
    private Integer realityTon;

    @Column(name = "cubicNum")
    @ApiModelProperty(value = "方量 搅拌站结算(平台与客户,平台与搅拌站签订的容重可能存在不一致,故分开计算容重)")
    private Double cubicNum;

    @Column(name = "cubicNum01")
    @ApiModelProperty(value = "方量 客户结算(平台与客户,平台与搅拌站签订的容重可能存在不一致,故分开计算容重)")
    private Double cubicNum01;

    @Column(name = "mortarTon")
    @ApiModelProperty(value = "砂浆净重 结算")
    private Integer mortarTon;

    @Column(name = "mortarCubicNum")
    @ApiModelProperty(value = "砂浆方量 搅拌站结算(平台与客户,平台与搅拌站签订的容重可能存在不一致,故分开计算容重)")
    private Double mortarCubicNum;

    @Column(name = "mortarCubicNum01")
    @ApiModelProperty(value = "砂浆方量 客户结算(平台与客户,平台与搅拌站签订的容重可能存在不一致,故分开计算容重)")
    private Double mortarCubicNum01;

    @Column(name = "compCubicNum")
    @ApiModelProperty(value = "小方补助方量")
    private Double compCubicNum;

    @Column(name = "compMoney")
    @ApiModelProperty(value = "小方补助金额")
    private Double compMoney;

    @Column(name = "unitPrice1")
    @ApiModelProperty(value = "含税单价 搅拌站")
    private Double unitPrice1;

    @Column(name = "unitPriceSum1")
    @ApiModelProperty(value = "含税金额 搅拌站")
    private Double unitPriceSum1;

    @Column(name = "unitSellingPrice1")
    @ApiModelProperty(value = "不含税单价 搅拌站")
    private Double unitSellingPrice1;

    @Column(name = "unitSellingPriceSum1")
    @ApiModelProperty(value = "不含税金额 搅拌站")
    private Double unitSellingPriceSum1;

    @Column(name = "priceMakup1")
    @ApiModelProperty(value = "加价(单价增加) 搅拌站 仅记录不参与计算")
    private Double priceMakup1;

    @Column(name = "unitPrice2")
    @ApiModelProperty(value = "含税单价 客户")
    private Double unitPrice2;

    @Column(name = "unitPriceSum2")
    @ApiModelProperty(value = "含税金额 客户")
    private Double unitPriceSum2;

    @Column(name = "unitSellingPrice2")
    @ApiModelProperty(value = "不含税单价 客户")
    private Double unitSellingPrice2;

    @Column(name = "unitSellingPriceSum2")
    @ApiModelProperty(value = "不含税金额 客户")
    private Double unitSellingPriceSum2;

    @Column(name = "priceMakup2")
    @ApiModelProperty(value = "加价(单价增加) 客户 仅记录不参与计算")
    private Double priceMakup2;

    @Column(name = "checkingNo1")
    @ApiModelProperty(value = "搅拌站对账编号")
    private String checkingNo1;

    @Column(name = "checkingNo2")
    @ApiModelProperty(value = "客户对账编号")
    private String checkingNo2;

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
