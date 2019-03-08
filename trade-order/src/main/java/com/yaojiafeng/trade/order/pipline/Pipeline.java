package com.yaojiafeng.trade.order.pipline;

import com.yaojiafeng.trade.order.common.ResultData;

/**
 *
 * 管道
 *
 * Created by yaojiafeng on 2019/3/7 7:50 PM.
 */
public interface Pipeline<T, S> {

    InvocationChain<T, S> newInvocation(T t, ResultData<S> resultData);

}
