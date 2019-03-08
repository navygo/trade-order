package com.yaojiafeng.trade.order.engine;

import com.yaojiafeng.trade.order.common.ResultData;

/**
 * Created by yaojiafeng on 2019/3/7 5:59 PM.
 */
public interface EngineExecutor<T, S> {

    ResultData<S> execute(T t);

}
