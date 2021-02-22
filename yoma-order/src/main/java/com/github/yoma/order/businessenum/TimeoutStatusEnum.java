package com.github.yoma.order.businessenum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yoma.common.utils.StringUtils;

/**
 * 网关用数据类型
 *
 * @author: msh01
 * @create: 2020/3/4
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TimeoutStatusEnum {
    Boolean(10, "超时"),  UTF_16(20, "未超时");

    public Integer code;
    public String text;

    TimeoutStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static TimeoutStatusEnum getEnum(Integer code) {
        TimeoutStatusEnum auditStatusEnum = null;
        if (code == null) {
        } else {
            TimeoutStatusEnum[] values = TimeoutStatusEnum.values();
            for (TimeoutStatusEnum x : values) {

                if (x.code.equals(code)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        return auditStatusEnum;
    }

    public static Integer getCodeByText(String text) {
        TimeoutStatusEnum auditStatusEnum = null;
        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            TimeoutStatusEnum[] values = auditStatusEnum.values();
            for (TimeoutStatusEnum x : values) {

                if (x.text.equalsIgnoreCase(text)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        if(auditStatusEnum ==null){
            throw new IllegalArgumentException("parameterDataTypeEnum不合法的text为"+text);
        }
        return auditStatusEnum.code;
    }
}
