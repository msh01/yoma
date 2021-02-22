package com.github.yoma.core.domain;

import javax.persistence.*;

import com.github.yoma.common.persistence.DataEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 集团/公司Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@Table(name = "core_company")
@ApiModel(value = "CoreCompany", description = "集团/公司信息表")
public class CoreCompany extends DataEntity<CoreCompany> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "公司主键")
    private Long id;

    @Column(name = "company_name")
    @ApiModelProperty(value = "公司名")
    private String companyName;
    @Column(name = "company_extra")
    @ApiModelProperty(value = "附加信息")
    private String companyExtra;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "所属集团")
    private Long parentId;

    @Column(name = "level")
    @ApiModelProperty(value = "公司层级")
    private Long level;

    @Column(name = "cascade_ids")
    @ApiModelProperty(value = "层级数组")
    private String cascadeIds;

    @Column(name = "company_full_name")
    @ApiModelProperty(value = "公司完整名称")
    private String companyFullName;

    @Column(name = "company_region")
    @ApiModelProperty(value = "所在地区")
    private Long companyRegion;

    @Column(name = "contact_name")
    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @Column(name = "contact_mobile")
    @ApiModelProperty(value = "联系人手机")
    private String contactMobile;

    @Column(name = "company_address")
    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @Column(name = "company_legal")
    @ApiModelProperty(value = "公司法人")
    private String companyLegal;

    @Column(name = "company_logo")
    @ApiModelProperty(value = "公司logo")
    private String companyLogo;

    @Column(name = "company_sn")
    @ApiModelProperty(value = "公司编码")
    private String companySn;

}
