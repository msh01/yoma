package com.github.yoma.order.service.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.github.yoma.order.businessenum.EffectiveStatusEnum;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月09日 22:46:00
 */
public class EffectiveStatusConverter implements Converter<Integer> {


    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
        GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        return EffectiveStatusEnum.getCodeByText(stringValue);
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty,
        GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
