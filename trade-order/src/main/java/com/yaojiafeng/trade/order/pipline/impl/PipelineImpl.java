package com.yaojiafeng.trade.order.pipline.impl;

import com.yaojiafeng.trade.order.common.PipelineException;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.Pipeline;
import com.yaojiafeng.trade.order.pipline.RollBack;
import com.yaojiafeng.trade.order.pipline.Success;
import com.yaojiafeng.trade.order.pipline.pipe.Pipe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 管道实现载体
 *
 * Created by yaojiafeng on 2019/3/7 8:20 PM.
 */
public class PipelineImpl<T, S> implements Pipeline<T, S> {

    private List<Pipe> pipeList = new ArrayList<>();

    public List<Pipe> getPipeList() {
        return pipeList;
    }

    public void setPipeList(List<Pipe> pipeList) {
        this.pipeList = pipeList;
    }

    @Override
    public InvocationChain<T, S> newInvocation(T t, ResultData<S> s) {
        return new InvocationImpl(t, s);
    }

    /**
     * 内部类
     * @param <T>
     * @param <S>
     */
    private final class InvocationImpl<T, S> implements InvocationChain<T, S> {

        private T parametr;

        private ResultData<S> result;

        private boolean broken;

        private Pipe breakPipe = null;

        private List<RollBack<T, S>> rollBackList = null;

        private List<Success<T, S>> successList = null;

        private int executedIndex = -1;

        private InvocationImpl() {
            rollBackList = new ArrayList<RollBack<T, S>>();
            successList = new ArrayList<Success<T, S>>();
        }

        public InvocationImpl(T parametr, ResultData<S> result) {
            this();
            this.parametr = parametr;
            this.result = result;
        }

        @Override
        public boolean isBroken() {
            return broken;
        }

        @Override
        public void invoke() throws PipelineException {
            if (isBroken()) {
                return;
            }
            executedIndex = -1;
            invokeNext();
        }

        @Override
        public boolean isFinished() {
            return !broken && executedIndex >= pipeList.size();
        }

        @Override
        public T getParameter() {
            return parametr;
        }

        @Override
        public ResultData<S> getResult() {
            return result;
        }

        @Override
        public List<Success<T, S>> getSuccessList() {
            return successList;
        }

        @Override
        public List<RollBack<T, S>> getRollBackList() {
            return rollBackList;
        }

        @Override
        public void breakPipeline(Pipe<T, S> pipe) {
            this.broken = true;
            this.breakPipe = pipe;
        }

        @Override
        public void invokeNext() throws PipelineException {
            if (isBroken()) {
                return;
            }

            executedIndex++;
            if (executedIndex < pipeList.size()) {
                Pipe pipe = pipeList.get(executedIndex);
                try {
                    pipe.invoke(this);
                } catch (PipelineException e) {
                    throw e;
                } catch (Exception e) {
                    throw new PipelineException("Failed to invoke : " + pipe.name(), e);
                }
                logger.info("invoke " + pipe.name() + " end");
            }

        }
    }
}
