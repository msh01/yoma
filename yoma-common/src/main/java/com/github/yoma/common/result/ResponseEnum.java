package com.github.yoma.common.result;

/**
 * 枚举类型定义
 *
 * @author yingw
 * @date 2019/12/11 11:23.
 */
public enum ResponseEnum {

    SUCCESS(1, "请求处理成功"),
    FAILURE(0, "请求处理失败"),
    UN_AUTHORIZED(401, "请求未认证，跳转登录页"),
    NOT_FOUND(406, "请求未授权，跳转未授权提示页"),

    ;

    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
