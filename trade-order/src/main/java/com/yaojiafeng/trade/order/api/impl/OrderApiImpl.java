package com.yaojiafeng.trade.order.api.impl;

import com.yaojiafeng.trade.order.api.OrderApi;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.engine.EngineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yaojiafeng on 2019/3/7 6:07 PM.
 */
@Service
public class OrderApiImpl implements OrderApi {

    @Autowired
    private EngineManager engineManager;

    @Override
    public ResultData<String> saveOrder(OrderForm orderForm) {
        return engineManager.<OrderForm, String>getEngineExecutor(orderForm.getOrderEngineExecutor()).execute(orderForm);
    }



}
