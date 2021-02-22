package com.github.yoma.common.utils.excel;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月11日 23:33:00
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * @author WuKun
 * @since 2019/10/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EasyExcelParams implements Serializable {

    /**
     * excel文件名（不带拓展名)
     */
    private String excelNameWithoutExt;
    /**
     * sheet名称
     */
    private String sheetName;

    /**
     * 数据
     */
    private List data;

    /**
     * 数据模型类型
     */
    private Class dataModelClazz;

    /**
     * 响应
     */
    private HttpServletResponse response;

    public EasyExcelParams() {}

    /**
     * 检查不允许为空的属性
     *
     * @return this
     */
    public EasyExcelParams checkValid() {
        Assert.isTrue(ObjectUtils.allNotNull(excelNameWithoutExt, data, dataModelClazz, response), "导出excel参数不合法!");
        return this;
    }
}
