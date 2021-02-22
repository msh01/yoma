package com.github.yoma.core.bussinessenum;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: msh01
 * @create: a
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleTypeEnum {

    system(11, "系统"), company(22, "公司"), project(33, "项目");

    public Integer code;
    public String text;

    RoleTypeEnum(Integer code, String text) {

        this.code = code;
        this.text = text;
    }
}
