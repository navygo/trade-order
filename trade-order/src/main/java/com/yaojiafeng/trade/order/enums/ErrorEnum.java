package com.yaojiafeng.trade.order.enums;

/**
 * Created by yaojiafeng on 2019/3/7 10:23 PM.
 */
public enum ErrorEnum {

    PARAMETER_ERROR("400", "参数错误"),

    SYSTEM_ERROR("500", "系统错误");

    private String code;

    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
