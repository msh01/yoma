package ${package}.service.dto;

import lombok.Data;
import javax.persistence.*;
<#if hasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
    import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if !auto && pkColumnType = 'Long'>
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>
/**
* @description Query对象 用来接收页面传递过来的查询条件参数,并将其传递到serveice层，dao层
* @author ${author}
* @date ${date}
**/
@ApiModel(value = "${className}QueryDTO", description="${apiAlias} 查询参数对象")
@Data
public class ${className}QueryDTO extends BaseQueryDTO  {

    private static final long serialVersionUID = 1L;
<#if queryColumns??>
    <#list queryColumns as column >
        @ApiModelProperty(value = "${column.remark}")
        private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>
<#if betweens??>
    <#list betweens as column>
        @ApiModelProperty(value = "范围筛选 ${column.remark}开始")
        private ${column.columnType} begin${column.changeColumnName?cap_first};
        @ApiModelProperty(value = "范围筛选 ${column.remark}结束")
        private ${column.columnType} end${column.changeColumnName?cap_first};
    </#list>
</#if>
}