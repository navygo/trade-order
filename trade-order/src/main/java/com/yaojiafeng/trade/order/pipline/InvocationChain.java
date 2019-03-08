package com.yaojiafeng.trade.order.pipline;

import com.yaojiafeng.trade.order.common.PipelineException;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.pipline.pipe.Pipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * 调用链封装类
 *
 * Created by yaojiafeng on 2019/3/7 8:21 PM.
 */
public interface InvocationChain<T, S> {

    Logger logger = LoggerFactory.getLogger(InvocationChain.class);

    void invoke() throws PipelineException;

    boolean isFinished();

    T getParameter();

    ResultData<S> getResult();

    List<Success<T, S>> getSuccessList();

    List<RollBack<T, S>> getRollBackList();

    boolean isBroken();

    void invokeNext() throws PipelineException;

    void breakPipeline(Pipe<T, S> pipe);

}
