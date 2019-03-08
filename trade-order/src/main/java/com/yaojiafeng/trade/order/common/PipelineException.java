package com.yaojiafeng.trade.order.common;

/**
 * Created by yaojiafeng on 2019/3/7 8:39 PM.
 */
public class PipelineException extends RuntimeException {

    public PipelineException() {
        super();
    }

    public PipelineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PipelineException(String message) {
        super(message);
    }

    public PipelineException(Throwable cause) {
        super(cause);
    }
}
