package com.yaojiafeng.trade.order.pipline;

/**
 *
 * 上下文储藏类，方法间传递参数
 *
 * Created by yaojiafeng on 2019/3/7 10:31 PM.
 */
public class PipeInnerContext {

    private Object context;

    public PipeInnerContext() {
    }

    public PipeInnerContext(Object context) {
        this.context = context;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

}
