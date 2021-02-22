package com.github.yoma.order.service.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.github.yoma.order.service.BetonClientService;
import com.github.yoma.tools.utils.SpringContext;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月09日 22:46:00
 */
public class CustomerIdConverter implements Converter<Long> {


    {

    }

    @Override
    public Class supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Long convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
        GlobalConfiguration globalConfiguration)   {
        BetonClientService service = SpringContext.getBean(BetonClientService.class);
        String stringValue = cellData.getStringValue();
        return service.getValueByText(stringValue);
    }

    @Override
    public CellData convertToExcelData(Long integer, ExcelContentProperty excelContentProperty,
        GlobalConfiguration globalConfiguration)  {
        return null;
    }
}
