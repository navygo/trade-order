package com.yaojiafeng.trade.order.example;

import com.yaojiafeng.trade.order.api.OrderApi;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.ItemDTO;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.enums.EngineExecutorEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * Created by yaojiafeng on 2019/3/7 7:48 PM.
 */
@SpringBootApplication
@ComponentScan("com.yaojiafeng.trade.order")
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class);
        OrderApi orderApi = applicationContext.getBean(OrderApi.class);
        OrderForm orderForm = new OrderForm();
        orderForm.setAddress("骆家庄");
        orderForm.setLogistic("笙歌物流");
        orderForm.setOrderEngineExecutor(EngineExecutorEnum.ORDER_ENGINE_EXECUTOR.getCode());

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(1L);
        itemDTO.setItemAmount(1L);
        orderForm.setItemDTOList(Arrays.asList(itemDTO));
        ResultData<String> resultData = orderApi.saveOrder(orderForm);
        System.err.println(resultData.getDefaultModel());


        // 虚拟商品
        orderForm.setOrderEngineExecutor(EngineExecutorEnum.VIRTUAL_ORDER_ENGINE_EXECUTOR.getCode());
        resultData = orderApi.saveOrder(orderForm);
        System.err.println(resultData.getDefaultModel());


    }
}
