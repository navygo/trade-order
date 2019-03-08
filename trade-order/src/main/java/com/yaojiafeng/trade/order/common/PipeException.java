package com.yaojiafeng.trade.order.common;

/**
 * Created by yaojiafeng on 2019/3/7 8:39 PM.
 */
public class PipeException extends RuntimeException {

    private String code;

    public PipeException(int code, String message) {
        super(message);
        this.code = String.valueOf(code);
    }

    public PipeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public PipeException(ResultData resultData) {
        super(resultData.getMessage());
        this.code = resultData.getCode();
    }

    public PipeException(Throwable e) {
        super(e);
    }

    public String getCode() {
        return code;
    }
}
