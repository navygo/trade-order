package com.yaojiafeng.trade.order.pipline;

/**
 * 失败回调，比如发下单失败消息
 *
 * Created by yaojiafeng on 2019/3/7 8:28 PM.
 */
public interface RollBack<T, S> {
    void rollBack(InvocationChain<T, S> invocationChain);
}
