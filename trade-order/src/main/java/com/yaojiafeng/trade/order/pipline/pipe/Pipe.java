package com.yaojiafeng.trade.order.pipline.pipe;

import com.yaojiafeng.trade.order.common.PipeException;
import com.yaojiafeng.trade.order.pipline.InvocationChain;

/**
 * 处理管道
 *
 * Created by yaojiafeng on 2019/3/7 9:11 PM.
 */
public interface Pipe<T, S> {

    String name();

    void invoke(InvocationChain<T, S> invocationChain) throws PipeException;

}
