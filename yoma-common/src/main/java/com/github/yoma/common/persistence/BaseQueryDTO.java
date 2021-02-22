package com.github.yoma.common.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.github.yoma.common.baseEnum.ArchiveEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询对象的父级抽象类
 */
@Data
public abstract class BaseQueryDTO {

    @ApiModelProperty("页码")
    private Integer pageNo;
    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    @ApiModelProperty("排序字符串")
    private String orderBy;

    @ApiModelProperty(value = "1 正常/未存档  11 已存档/逻辑删除")
    private Integer archive;

    @ApiModelProperty(value = "数据库类型", hidden = true)
    private String dbName = "mysql";

    @ApiModelProperty(value = "是否来自移动端", hidden = true)
    private Boolean isMobile = true;

    @ApiModelProperty(value = "当前登录用户信息的封装", hidden = true)
    private UserBean userBean;

    @ApiModelProperty("关键字模糊查询的字符串参数")
    private String keywordSearchContent;

    @ApiModelProperty(value = "批量操作传参用(例如导出选中记录)，当前表的主键id list", hidden = true)
    private List<Long> batchIdList;

    @ApiModelProperty(value = "get请求不支持传递数组类型参数，所以需用逗号隔开的字符串传递批量操作的id")
    private String batchIdStr;

    @ApiModelProperty(value = "创建人id", hidden = true)
    protected String createBy;

    @ApiModelProperty(value = "修改人id", hidden = true)
    protected String updateBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    protected LocalDateTime createTime;

    @ApiModelProperty(value = "创建日期", hidden = true)
    protected LocalDate createDate;

    @ApiModelProperty(value = "修改时间", hidden = true)
    protected LocalDateTime updateTime;

    public Integer getArchive() {
        if (this.archive == null) {
            return ArchiveEnum.normal.code;
        }
        return archive;
    }

    // added by msh 20201216 适配页面传参。因为有的可能不叫pageNo , 而是叫 page;
    // 另外，原有的后端接口，用的spring data jpa，pageno 是从零开始计数的。为了适配原有的逻辑，后端页码需要加一
    public Integer getPageNo() {
        if (this.page != null) {
            return this.page + 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        // 设置默认值，防止空指向异常
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return pageSize;
    }

    @ApiModelProperty(hidden = true)
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * 如果batchIdStr不为空且batchIdList为空，则基于传递过来的逗号隔开的字符串来进行转换，赋值给batchIdList
     *
     * @return
     */
    public List<Long> getBatchIdList() {
        if (StringUtils.isNotEmpty(this.batchIdStr) && !(batchIdList != null && !batchIdList.isEmpty())) {
            String[] batchIdArray = batchIdStr.split(",");
            batchIdList = Stream.of(batchIdArray).map(Long::parseLong).collect(Collectors.toList());
        } else {
        }
        return batchIdList;
    }
}
