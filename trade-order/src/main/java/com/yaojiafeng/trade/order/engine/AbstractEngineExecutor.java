package com.yaojiafeng.trade.order.engine;

import com.yaojiafeng.trade.order.engine.impl.DefaultEngineExecutor;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.RollBack;
import com.yaojiafeng.trade.order.pipline.Success;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by yaojiafeng on 2019/3/7 6:09 PM.
 */
public abstract class AbstractEngineExecutor<T, S> implements EngineExecutor<T, S> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEngineExecutor.class);

    @Autowired
    private EngineManager engineManager;

    private String key;

    @PostConstruct
    public void init() {
        engineManager.register(getKey(), this);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected void doSuccess(InvocationChain<T, S> invocationChain) {
        List<Success<T, S>> successList = invocationChain.getSuccessList();
        if (CollectionUtils.isNotEmpty(successList)) {
            for (Success<T, S> success : successList) {
                success.success(invocationChain);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("AbstractEngineExecutor success list is empty");
            }
        }
    }

    protected void doRollback(InvocationChain<T, S> invocationChain) {
        List<RollBack<T, S>> rollBackList = invocationChain.getRollBackList();
        if (CollectionUtils.isNotEmpty(rollBackList)) {
            for (RollBack<T, S> rollBack : rollBackList) {
                rollBack.rollBack(invocationChain);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("AbstractEngineExecutor rollback list is empty");
            }
        }
    }

}
