package com.yaojiafeng.trade.order.engine;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaojiafeng on 2019/3/7 5:54 PM.
 */
@Component
public class EngineManager {

    private Map<String, EngineExecutor> engineExecutorMap = new HashMap<>();

    public void register(String key, EngineExecutor engineExecutor) {
        engineExecutorMap.put(key, engineExecutor);
    }

    public <T, S> EngineExecutor<T, S> getEngineExecutor(String key) {
        return engineExecutorMap.get(key);
    }
}
