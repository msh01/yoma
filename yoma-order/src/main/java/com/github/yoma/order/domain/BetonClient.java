package com.github.yoma.order.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商砼客户Entity    既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
 * 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-11-20
 */
@Data
@Table(name = "t_beton_client")
@ApiModel(value = "BetonClient", description = "商砼客户列表")
public class BetonClient extends DataEntity<BetonClient> {
    String dd = "";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "clientId")
    @ApiModelProperty(value = "搅拌站ID 关联 t_client_infor 的 loginAppId")
    private Long clientId;

    @Column(name = "clientNo")
    @ApiModelProperty(value = "客户编号")
    private String clientNo;

    @Column(name = "clientName")
    @ApiModelProperty(value = "客户名称")
    private String clientName;

    @Column(name = "clientType")
    @ApiModelProperty(value = "客户类型 1-普通客户")
    private Integer clientType;

    @Column(name = "sex")
    @ApiModelProperty(value = "性别 1-男 2-女")
    private Integer sex;

    @Column(name = "checkType")
    @ApiModelProperty(value = "审核状态 0-审核通过 1-未审核 2-审核未通过")
    private Integer checkType;

    @Column(name = "state")
    @ApiModelProperty(value = "状态 0-正常 99-删除")
    private Integer state;

    @Column(name = "createTime")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Column(name = "createId")
    @ApiModelProperty(value = "创建人ID")
    private Long createId;

    @Column(name = "createName")
    @ApiModelProperty(value = "创建人姓名")
    private String createName;


}
