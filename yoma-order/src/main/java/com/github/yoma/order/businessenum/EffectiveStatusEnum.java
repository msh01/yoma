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
public enum EffectiveStatusEnum {
    efficient(10, "有效"),  not_efficient(20, "无效");

    public Integer code;
    public String text;

    EffectiveStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static EffectiveStatusEnum getEnum(Integer code) {
        EffectiveStatusEnum auditStatusEnum = null;
        if (code == null) {
        } else {
            EffectiveStatusEnum[] values = EffectiveStatusEnum.values();
            for (EffectiveStatusEnum x : values) {

                if (x.code.equals(code)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        return auditStatusEnum;
    }

    public static Integer getCodeByText(String text) {
        EffectiveStatusEnum auditStatusEnum = null;
        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            EffectiveStatusEnum[] values = auditStatusEnum.values();
            for (EffectiveStatusEnum x : values) {

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
