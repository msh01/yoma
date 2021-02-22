package com.github.yoma.tools.businessenum;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 网关用数据类型
 *
 * @author: msh01
 * @create: 2020/3/4
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ClickActionTypeEnum {
    self(1, "用户自定义点击行为"), url(2, "点击后打开特定url"),
    app(3, "点击后打开应用App"), media(4, "点击后打开富媒体信息");

    public Integer code;
    public String text;

    ClickActionTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

}
