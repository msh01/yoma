package com.github.yoma.order.service.excel.converter;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月11日 23:41:00
 */

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Date and number converter
 *
 * @author Jiaju Zhuang
 */
public class LocalDateNumberConverter implements Converter<LocalDate> {

    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            Date javaDate = DateUtil.getJavaDate(cellData.getNumberValue().doubleValue(),
                globalConfiguration.getUse1904windowing(), null);
            LocalDate localDate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate;
        } else {
            Date javaDate = DateUtil.getJavaDate(cellData.getNumberValue().doubleValue(),
                contentProperty.getDateTimeFormatProperty().getUse1904windowing(), null);
            LocalDate localDate = javaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate;
        }
    }

    @Override
    public CellData convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            Date date = Date.from(value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            return new CellData(
                BigDecimal.valueOf(DateUtil.getExcelDate(date, globalConfiguration.getUse1904windowing())));
        } else {
            Date date = Date.from(value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            return new CellData(BigDecimal.valueOf(
                DateUtil.getExcelDate(date, contentProperty.getDateTimeFormatProperty().getUse1904windowing())));
        }
    }
}
