package com.yaojiafeng.trade.order.pipline.pipe;

import com.yaojiafeng.trade.order.common.PipeException;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.enums.ErrorEnum;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.PipeInnerContext;
import com.yaojiafeng.trade.order.pipline.RollBack;
import com.yaojiafeng.trade.order.pipline.Success;
import com.yaojiafeng.trade.order.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

/**
 * 抽象管道
 *
 * Created by yaojiafeng on 2019/3/7 9:28 PM.
 */
public abstract class AbstractPipe<T, S> implements Pipe<T, S> {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractPipe.class);

    @Override
    public void invoke(InvocationChain<T, S> invocationChain) throws PipeException {
        PipeException exception = null;
        // 获取运行的参数
        T t = invocationChain.getParameter();
        // 获取运行结果
        ResultData<S> resultData = invocationChain.getResult();
        if (resultData == null || !resultData.isSuccess()) {
            logger.warn("invocationChain resultData fail ,{} ", invocationChain);
            invocationChain.breakPipeline(this);
            return;
        }

        if (t == null) {
            logger.warn("invocationChain parameter is null,{} ", invocationChain);
            invocationChain.breakPipeline(this);
            return;
        }

        PipeInnerContext pipeInnerContext = new PipeInnerContext();

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("invocationChain verifyParameter {}", invocationChain);
            }
            // 校验业务参数
            this.verify(t, resultData);

            // 判断业务参数结果
            if (resultData == null || !resultData.isSuccess()) {
                try {
                    this.failAfterHandler(invocationChain, pipeInnerContext);
                } catch (Throwable e) {
                    logger.error("failAfterHandler verifyParameter error " + invocationChain, new PipeException(e));
                }
                logger.warn("verifyParameter fail, pipe = {}, invocationChain = {}", this.getClass().getSimpleName(), invocationChain);
                // 失败跳出
                invocationChain.breakPipeline(this);
                return;
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("pipe bizHandler invocationChain = {}", invocationChain);
                }
                // 执行业务处理
                pipeInnerContext = this.handler(t, resultData, pipeInnerContext);
            }
        } catch (PipeException e) {
            exception = e;
            resultData.setCode(e.getCode());
            resultData.setMessage(e.getMessage());
        } catch (Throwable e) {
            exception = new PipeException(e);
            resultData = ResultUtils.build(ErrorEnum.SYSTEM_ERROR, resultData);
        }

        // 判断业务处理结果
        if (resultData == null || !resultData.isSuccess()) {
            try {
                this.failAfterHandler(invocationChain, pipeInnerContext);
            } catch (Throwable e) {
                logger.error("failAfterHandler bizHandler error " + invocationChain, e);
            }
            // 失败跳出
            invocationChain.breakPipeline(this);
            rollbackAdd(invocationChain);
            // 如果业务有异常抛出,则需要重新抛出异常
            if (!Objects.isNull(exception)) {
                throw exception;
            }
        } else {
            try {
                this.successAfterHandler(invocationChain, pipeInnerContext);
            } catch (Exception e) {
                logger.error("successAfterHandler bizHandler error " + invocationChain, new PipeException(e));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("pipe invoke next, pipe = {},invocationChain = {}", this.getClass().getName(), invocationChain);
            }
            // 执行下一个流程
            invokeNext(invocationChain);
        }
    }

    protected void verify(T t, ResultData<S> resultData) {
        verifyParameter(t, resultData);
    }

    protected void failAfterHandler(InvocationChain<T, S> invocationChain, PipeInnerContext pipeInnerContext) {

    }

    protected void successAfterHandler(InvocationChain<T, S> invocationChain, PipeInnerContext pipeInnerContext) {
    }

    protected abstract void verifyParameter(T t, ResultData<S> resultData);

    protected PipeInnerContext handler(T t, ResultData<S> resultData, PipeInnerContext pipeInnerContext) {
        bizHandler(t, resultData, pipeInnerContext);
        return pipeInnerContext;
    }

    protected abstract void bizHandler(T t, ResultData<S> resultData, PipeInnerContext pipeInnerContext);

    protected void invokeNext(InvocationChain<T, S> invocationChain) {
        successAdd(invocationChain);
        rollbackAdd(invocationChain);
        invocationChain.invokeNext();
    }

    private void successAdd(InvocationChain<T, S> invocationChain) {
        List<Success<T, S>> successList = invocationChain.getSuccessList();
        if (this instanceof Success && successList != null) {
            successList.add((Success) this);
        }
    }

    private void rollbackAdd(InvocationChain<T, S> invocationChain) {
        List<RollBack<T, S>> rollBackList = invocationChain.getRollBackList();
        if (this instanceof RollBack && rollBackList != null) {
            rollBackList.add((RollBack) this);
        }
    }
}
