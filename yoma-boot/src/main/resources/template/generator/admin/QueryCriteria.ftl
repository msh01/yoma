/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package ${package}.service.dto;

import lombok.Data;
<#if queryHasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if queryHasBigDecimal>
    import java.math.BigDecimal;
</#if>
<#if betweens??>
    import java.util.List;
</#if>
<#if queryColumns??>
    import me.zhengjie.annotation.Query;
</#if>

/**
* @website https://el-admin.vip
* @author ${author}
* @date ${date}
**/
@Data
public class ${className}QueryCriteria{
<#if queryColumns??>
    <#list queryColumns as column>

        <#if column.queryType = '='>
            /** 精确 */
            @Query
            private ${column.columnType} ${column.changeColumnName};
        </#if>
        <#if column.queryType = 'Like'>
            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private ${column.columnType} ${column.changeColumnName};
        </#if>
        <#if column.queryType = '!='>
            /** 不等于 */
            @Query(type = Query.Type.NOT_EQUAL)
            private ${column.columnType} ${column.changeColumnName};
        </#if>
        <#if column.queryType = 'NotNull'>
            /** 不为空 */
            @Query(type = Query.Type.NOT_NULL)
            private ${column.columnType} ${column.changeColumnName};
        </#if>
        <#if column.queryType = '>='>
            /** 大于等于 */
            @Query(type = Query.Type.GREATER_THAN)
            private ${column.columnType} ${column.changeColumnName};
        </#if>
        <#if column.queryType = '<='>
            /** 小于等于 */
            @Query(type = Query.Type.LESS_THAN)
            private ${column.columnType} ${column.changeColumnName};
        </#if>
    </#list>
</#if>
<#if betweens??>
    <#list betweens as column>
        /** BETWEEN */
        @Query(type = Query.Type.BETWEEN)
        private List<${column.columnType}> ${column.changeColumnName};
    </#list>
</#if>
}


<?xml version="1.0" encoding="UTF-8" ?>
<mapper namespace="${package}.dao.${className}Dao">

    <#-- 输出字段列 ,并去除末位引号-->
    <sql id="${className}Columns">
        <#assign columnField>
            <#list columns as column>
                a.${column.columnName} AS "${column.changeColumnName}",
            </#list>
        </#assign>
        ${columnField?substring(0, columnField?last_index_of(","))}
    </sql>

    <#-- 输出字段关联表 -->
    <sql id="${className}Joins">
    </sql>

    <select id="get" resultType="${className}">
        SELECT
        <include refid="${className}Columns"/>
        FROM ${tableName} a
        <include refid="${className}Joins"/>
        WHERE a.id = ${"#"}{id}
    </select>

    <select id="findList" resultType="${className}">
        SELECT
        <include refid="${className}Columns"/>
        FROM ${tableName} a
        <include refid="${className}Joins"/>
        <where>
            <#if queryColumns??>
                <#list queryColumns as column >
                    <if test="${column.changeColumnName} != null<#if column.changeColumnName != column.changeColumnName> and ${column.changeColumnName} != null</#if>   <#if column.columnType == 'String'> and ${column.changeColumnName} != ''</#if>  ">
                        <#if column.queryType = '='>
                            AND a.${column.columnName} =  ${"#"}{${column.changeColumnName}}
                        </#if>
                        <#if column.queryType = 'Like'>
                            AND a.${column.columnName} LIKE concat('%',${"#"}{${column.changeColumnName}},'%')
                        </#if>
                        <#if column.queryType = '!='>
                            AND a.${column.columnName} !=  ${"#"}{${column.changeColumnName}}
                        </#if>
                        <#if column.queryType = 'NotNull'>
                            AND a.${column.columnName} is not null
                        </#if>
                        <#if column.queryType = '>='>
                            AND a.${column.columnName}  &gt;  ${"#"}{${column.changeColumnName}}
                        </#if>
                        <#if column.queryType = '<='>
                            AND a.${column.columnName}  &lt;  ${"#"}{${column.changeColumnName}}
                        </#if>
                    </if>
                </#list>
            </#if>
            <if test="batchIdList != null  and !batchIdList.isEmpty()  ">
                AND
                id IN
                <foreach collection="batchIdList" item="id" index="index"
                         open="(" close=")" separator=",">
                    ${"#"}{id}
                </foreach>
            </if>
        </where>
        ORDER BY
        <choose>
            <when test="orderBy != null and orderBy != ''">
                ${"$"}{orderBy} a.id desc
            </when>
            <otherwise>
                a.id desc
            </otherwise>
        </choose>
    </select>


</mapper>