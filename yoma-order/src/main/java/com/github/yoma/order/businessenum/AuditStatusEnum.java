package com.github.yoma.order.businessenum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.yoma.common.utils.StringUtils;

/**
 *
 *   this.createTypes = [{ code: 0, text: "手动" }, { code: 1, text: "自动" }]
 *       this.periods = [{ code: 1, text: "年" }, { code: 2, text: "月" }, { code: 3, text: "日" }]
 *       this.rateTypes = [{ code: 0, text: "固定值" }, { code: 1, text: "实际值" }]
 *       this.priceMakupTypes = [{ code: 0, text: "加价" }, { code: 1, text: "不加价" }]
 *       this.compTypes = [{ code: 0, text: "无补助" }, { code: 1, text: "有补助" }]
 * 网关用数据类型
 *
 * @author: msh01
 * @create: 2020/3/4
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuditStatusEnum {
    Audited(10, "已审"),  Not_Audited(20, "未审");

    public Integer code;
    public String text;

    AuditStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static AuditStatusEnum getEnum(Integer code) {
        AuditStatusEnum auditStatusEnum = null;
        if (code == null) {
        } else {
            AuditStatusEnum[] values = AuditStatusEnum.values();
            for (AuditStatusEnum x : values) {

                if (x.code.equals(code)) {
                    auditStatusEnum = x;
                    break;
                }
            }
        }
        return auditStatusEnum;
    }

    public static Integer getCodeByText(String text) {
        AuditStatusEnum auditStatusEnum = null;
        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            AuditStatusEnum[] values = auditStatusEnum.values();
            for (AuditStatusEnum x : values) {

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
