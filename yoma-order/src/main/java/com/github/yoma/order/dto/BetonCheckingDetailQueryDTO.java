package com.github.yoma.order.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.github.yoma.common.persistence.BaseQueryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对账明细 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 *
 * @author 马世豪
 * @version 2020-12-02
 */
@Data
@ApiModel("对账明细表存每日的对账明细纬度:搅拌站-客户-项目-砼标号")
public class BetonCheckingDetailQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "范围筛选 数据日期开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDataDate;

    @ApiModelProperty(value = "范围筛选 数据日期结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDataDate;

    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @ApiModelProperty(value = "商砼客户ID 关联 t_beton_client")
    private Long betonClientId;

    @ApiModelProperty(value = "工程信息ID 关联 t_beton_project")
    private Long projectId;

    @ApiModelProperty(value = "浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸")
    private Integer castType;
    @ApiModelProperty(value = "检查是否存在未审核或审核失败数据  0-检查 1-不检查")
    private Integer type;

    @ApiModelProperty(value = "砼标号")
    private String conStrength;
    @ApiModelProperty(value = "砼标号")
    private Integer distinctConStrength;

    @ApiModelProperty(value = "搅拌站对账编号")
    private String checkingNo1;

    @ApiModelProperty(value = "客户对账编号")
    private String checkingNo2;

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
