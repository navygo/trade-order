package com.yaojiafeng.trade.order.pipline.pipe.impl;

import com.alibaba.fastjson.JSON;
import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.enums.ErrorEnum;
import com.yaojiafeng.trade.order.enums.PipeEnum;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.PipeInnerContext;
import com.yaojiafeng.trade.order.pipline.RollBack;
import com.yaojiafeng.trade.order.pipline.Success;
import com.yaojiafeng.trade.order.pipline.pipe.AbstractPipe;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 保存订单
 *
 * Created by yaojiafeng on 2019/3/7 11:07 PM.
 */
public class OrderPipe extends AbstractPipe<OrderForm, String> implements RollBack<OrderForm, String>, Success<OrderForm, String> {

    private static final Logger logger = LoggerFactory.getLogger(OrderPipe.class);

    @Override
    protected void verifyParameter(OrderForm orderForm, ResultData<String> resultData) {
        if (CollectionUtils.isEmpty(orderForm.getItemDTOList())) {
            resultData.setCode(ErrorEnum.PARAMETER_ERROR.getCode());
            resultData.setCode("商品不能为空");
        }
    }

    @Override
    protected void bizHandler(OrderForm orderForm, ResultData<String> resultData, PipeInnerContext pipeInnerContext) {
        System.out.println(JSON.toJSONString(orderForm.getItemDTOList()));
        resultData.setDefaultModel(UUID.randomUUID().toString());
    }

    @Override
    public String name() {
        return PipeEnum.ORDER_PIPE.name();
    }

    @Override
    public void rollBack(InvocationChain<OrderForm, String> invocationChain) {
        logger.error("rollBack");
    }

    @Override
    public void success(InvocationChain<OrderForm, String> invocationChain) {
        logger.info("success");
    }
}
