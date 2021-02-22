package com.github.yoma.core.bussinessenum;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: msh01
 * @create: a
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeviceBrandEnum {

    HUAWEI(11, "系统"), XIAOMI(22, "公司"), OPPO(33, "项目"), MEIZU(33, "项目");

    public Integer code;
    public String text;

    DeviceBrandEnum(Integer code, String text) {

        this.code = code;
        this.text = text;
    }
}
