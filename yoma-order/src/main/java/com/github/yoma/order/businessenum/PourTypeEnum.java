package com.github.yoma.order.businessenum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yoma.common.utils.StringUtils;

/**
 * 浇筑方式 1-地泵 2-气泵 3-塔吊 4-自卸
 *
 * @author: msh01
 * @create: 2020/3/4
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PourTypeEnum {
    dibeng(1, "地泵"), qibeng(2, "汽泵"), tadiao(3, "塔吊"), zixie(4, "自卸"), other(10, "其它 ");

    public Integer code;
    public String text;

    PourTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static PourTypeEnum getEnum(Integer code) {
        PourTypeEnum auditStatusEnum = null;
        if (code == null) {
        } else {
            PourTypeEnum[] values = PourTypeEnum.values();
            for (PourTypeEnum x : values) {

                if (x.code.equals(code)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        return auditStatusEnum;
    }

    public static Integer getCodeByText(String text) {
        PourTypeEnum auditStatusEnum = null;
        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            PourTypeEnum[] values = PourTypeEnum.values();
            for (PourTypeEnum x : values) {

                if (x.text.equalsIgnoreCase(text)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        if (auditStatusEnum == null) {
            auditStatusEnum = PourTypeEnum.other;
        }
        return auditStatusEnum.code;
    }
}
