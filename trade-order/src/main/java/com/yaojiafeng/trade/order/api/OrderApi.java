package com.yaojiafeng.trade.order.api;

import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.OrderForm;

/**
 * Created by yaojiafeng on 2019/3/7 6:07 PM.
 */
public interface OrderApi {

    /**
     * 提交保存订单
     *
     *
     * @param orderForm
     * @return
     */
    ResultData<String> saveOrder(OrderForm orderForm);

}
