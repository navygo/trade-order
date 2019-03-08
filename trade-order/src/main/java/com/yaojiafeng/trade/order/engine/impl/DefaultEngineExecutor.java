package com.yaojiafeng.trade.order.engine.impl;

import com.yaojiafeng.trade.order.common.PipelineException;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.engine.AbstractEngineExecutor;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Objects;

/**
 * Created by yaojiafeng on 2019/3/7 6:04 PM.
 */
public class DefaultEngineExecutor<T, S> extends AbstractEngineExecutor<T, S> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEngineExecutor.class);

    private Pipeline pipeline;

    @Override
    public ResultData<S> execute(T t) {

        ResultData<S> resultData = new ResultData<S>();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start("DefaultEngineExecutor begin");
            InvocationChain<T, S> invocationChain = pipeline.newInvocation(t, resultData);
            RuntimeException throwable = null;
            try {
                invocationChain.invoke();
            } catch (PipelineException e) {
                logger.error("Pipeline execute fail", e);
                throwable = e;
                resultData.setCode(ResultData.FAIL);
            } catch (Throwable e) {
                logger.error("Pipeline execute fail", e);
                throwable = new PipelineException(e);
                resultData.setCode(ResultData.FAIL);
            }

            if (invocationChain.isFinished()) {
                if (resultData.isSuccess()) {
                    doSuccess(invocationChain);
                } else {
                    doRollback(invocationChain);
                }
            } else {
                doRollback(invocationChain);
            }
            if (!Objects.isNull(throwable)) {
                throw throwable;
            }
        } finally {
            stopWatch.stop();
            logger.info(stopWatch.prettyPrint());
        }

        return resultData;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }
}
