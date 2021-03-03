<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
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

            <#if betweens??>
                <#list betweens as column>
                    <if test="${column.changeColumnName} != null<#if column.changeColumnName != column.changeColumnName> and ${column.changeColumnName} != null</#if>   <#if column.columnType == 'String'> and ${column.changeColumnName} != ''</#if>  ">
                        AND a.${column.columnName}
                        BETWEEN ${"#"}{begin${column.changeColumnName?cap_first}} AND ${"#"}{end${column.changeColumnName?cap_first}}
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

    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${tableName}(
        <#assign insertField>
            <#list columns as column>
                <#if column.columnKey != 'PRI'>
                    ${column.columnName},
                </#if>
            </#list>
        </#assign>
        ${insertField?substring(0, insertField?last_index_of(","))}
        ) VALUES (
        <#assign insertJavaField>
            <#list columns as column>
                <#if column.columnKey != 'PRI'>
                    ${"#"}{${column.changeColumnName}},
                </#if>
            </#list>
        </#assign>
        ${insertJavaField?substring(0, insertJavaField?last_index_of(","))}
        )
    </insert>

    <insert id="batchInsert" useGeneratedKeys="true" keyProperty="id" parameterType="java.util.List">
        INSERT INTO ${tableName}
        (
        <#assign insertField>
            <#list columns as column>
                <#if column.columnKey != 'PRI'>
                    ${column.columnName},
                </#if>
            </#list>
        </#assign>
        ${insertField?substring(0, insertField?last_index_of(","))}
        )
        values
        <foreach collection="list" item="item" index="index" separator=",">
            (
            <#assign insertJavaField>
                <#list columns as column>
                    <#if column.columnKey != 'PRI'>
                        ${"#"}{item.${column.changeColumnName}},
                    </#if>
                </#list>
            </#assign>
            ${insertJavaField?substring(0, insertJavaField?last_index_of(","))}
            )
        </foreach>
    </insert>

    <update id="update">
        UPDATE ${tableName}
        <set>
            <#list columns as column>
                <if test="${column.changeColumnName} != null <#if column.columnType == 'String'>and ${column.changeColumnName} != ''</#if> ">
                    ${column.columnName} = ${"#"}{${column.changeColumnName}},
                </if>
            </#list>
        </set>
        <where>
            id = ${"#"}{id}
        </where>
    </update>

    <delete id="delete">
        <#--<#if table.delFlagExists>
            UPDATE ${tableName} SET
            delFlag= ${"#"}{DEL_FLAG_DELETE}
        <#else>
        </#if>-->
        DELETE FROM ${tableName}
        <#-- <#if table.parentExists>
             <#list columns as column>
                 <#if table.parentTableFk == column.columnName>
                     <choose>
                         <when test="id !=null and id != ''">
                             <where> ${table.primaryKeyColumnName} = ${"#"}{id} </where>
                         </when>
                         <otherwise>
                             <where> ${table.parentTableFk} = ${"#"}{${column.changeColumnName}} </where>
                         </otherwise>
                     </choose>
                 </#if>
             </#list>
         <#else>
         </#if>-->
        <where> id= ${"#"}{id}</where>
    </delete>


    <delete id="batchDelete">
        DELETE FROM ${tableName}
        <where>
            id IN
            <foreach collection="batchIdList" item="id" index="index"
                     open="(" close=")" separator=",">
                ${"#"}{id}
            </foreach>
        </where>
    </delete>

</mapper>