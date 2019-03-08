package com.yaojiafeng.trade.order.example.config;

import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.engine.EngineExecutor;
import com.yaojiafeng.trade.order.engine.impl.DefaultEngineExecutor;
import com.yaojiafeng.trade.order.enums.EngineExecutorEnum;
import com.yaojiafeng.trade.order.pipline.impl.PipelineImpl;
import com.yaojiafeng.trade.order.pipline.pipe.Pipe;
import com.yaojiafeng.trade.order.pipline.pipe.impl.AddressPipe;
import com.yaojiafeng.trade.order.pipline.pipe.impl.LogisticPipe;
import com.yaojiafeng.trade.order.pipline.pipe.impl.OrderPipe;
import com.yaojiafeng.trade.order.pipline.pipe.impl.StockPipe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 配置bean替代xml，实现管道复用
 *
 * Created by yaojiafeng on 2019/3/7 11:21 PM.
 */
@Configuration
public class Config {

    @Bean
    public EngineExecutor orderEngineExecutor() {
        DefaultEngineExecutor<OrderForm, String> defaultEngineExecutor = new DefaultEngineExecutor<OrderForm, String>();
        defaultEngineExecutor.setKey(EngineExecutorEnum.ORDER_ENGINE_EXECUTOR.getCode());

        PipelineImpl pipeline = new PipelineImpl();

        List<Pipe> pipeList = new ArrayList<>();

        pipeList.add(addressPipe());
        pipeList.add(logisticPipe());
        pipeList.add(orderPipe());
        pipeList.add(stockPipe());
        pipeline.setPipeList(pipeList);

        defaultEngineExecutor.setPipeline(pipeline);

        return defaultEngineExecutor;
    }


    @Bean
    public Pipe orderPipe() {
        OrderPipe orderPipe = new OrderPipe();
        return orderPipe;
    }

    @Bean
    public Pipe addressPipe() {
        AddressPipe addressPipe = new AddressPipe();
        return addressPipe;
    }

    @Bean
    public Pipe logisticPipe() {
        LogisticPipe logisticPipe = new LogisticPipe();
        return logisticPipe;
    }

    @Bean
    public Pipe stockPipe() {
        StockPipe stockPipe = new StockPipe();
        return stockPipe;
    }


    @Bean
    public EngineExecutor virtualOrderEngineExecutor() {
        DefaultEngineExecutor<OrderForm, String> defaultEngineExecutor = new DefaultEngineExecutor<OrderForm, String>();
        defaultEngineExecutor.setKey(EngineExecutorEnum.VIRTUAL_ORDER_ENGINE_EXECUTOR.getCode());

        PipelineImpl pipeline = new PipelineImpl();

        List<Pipe> pipeList = new ArrayList<>();

        pipeList.add(orderPipe());
        pipeline.setPipeList(pipeList);

        defaultEngineExecutor.setPipeline(pipeline);

        return defaultEngineExecutor;
    }
}
