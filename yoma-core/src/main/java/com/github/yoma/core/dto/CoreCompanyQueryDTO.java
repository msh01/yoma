package com.github.yoma.core.dto;

import com.github.yoma.common.persistence.BaseQueryDTO;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 集团/公司 Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层。
 * @author 马世豪
 * @version 2020-04-07
 */
@Data
@ApiModel("集团/公司信息表")
public class CoreCompanyQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value = "所属集团")
    private Long parentId;

    @ApiModelProperty(value = "公司层级")
    private Long level;

    @ApiModelProperty(value = "层级数组")
    private String cascadeIds;

    @ApiModelProperty(value = "公司完整名称")
    private String companyFullName;

    @ApiModelProperty(value = "所在地区")
    private Long companyRegion;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人手机")
    private String contactMobile;

    @ApiModelProperty(value = "公司法人")
    private String companyLegal;

    @ApiModelProperty(value = "公司编码")
    private String companySn;

}
