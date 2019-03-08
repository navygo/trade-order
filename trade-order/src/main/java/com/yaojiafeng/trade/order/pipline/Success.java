package com.yaojiafeng.trade.order.pipline;

/**
 * 成功回调，比如下单成功发消息
 *
 * Created by yaojiafeng on 2019/3/7 8:28 PM.
 */
public interface Success<T, S> {
    void success(InvocationChain<T, S> invocationChain);
}
