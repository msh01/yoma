package com.github.yoma.order.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.github.yoma.order.service.excel.converter.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商砼运输订单Entity 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据） 基于lombok ，所以无需生成getter 和 setter方法
 *
 * @author 马世豪
 * @version 2020-11-19
 */
@ApiModel(value = "BetonOrder", description = "商砼运输订单明细表")
public class DictVo {

    private static final long serialVersionUID = 1L;

    // @Column(name = "orderNo")
    // @ExcelProperty(value = "运输单号", index = 0)
    // @ApiModelProperty(value = "订单编号")
    // private String orderNo;

    @Column(name = "所有的数据字典集合，用枚举值来填充")
    private Map<String, Object> dicts;

}
