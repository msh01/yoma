package com.github.yoma.common.baseEnum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yoma.common.utils.StringUtils;

/**
 * 1 正常/未存档  11 已存档/逻辑删除
 * @author: msh01
 * @create: 2020/10/27
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ArchiveEnum {
    normal(1, "正常/未存档 "), deleted(11, "已存档/逻辑删除"),;

    public Integer code;
    public String text;

    ArchiveEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取info
     */
    public static String getInfoByCode(Integer code) {
        for (ArchiveEnum statusEnum : ArchiveEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum.text;
            }
        }
        return null;
    }

    public static Integer getCodeByInfo(String info) {
        if (StringUtils.isNotEmpty(info)) {
            info = StringUtils.trim(info);
        }
        for (ArchiveEnum statusEnum : ArchiveEnum.values()) {
            if (statusEnum.text.equals(info)) {
                return statusEnum.code;
            }
        }
        return null;
    }

    public static ArchiveEnum getEnum(Integer code) {
        ArchiveEnum anEnum = null;
        if (code == null) {
        } else {
            ArchiveEnum[] values = ArchiveEnum.values();
            for (ArchiveEnum x : values) {

                if (x.code.equals(code)) {
                    anEnum = x;
                    break;
                }
            }
        }
        return anEnum;
    }
}
