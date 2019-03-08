package com.yaojiafeng.trade.order.pipline.pipe.impl;

import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.enums.ErrorEnum;
import com.yaojiafeng.trade.order.enums.PipeEnum;
import com.yaojiafeng.trade.order.pipline.PipeInnerContext;
import com.yaojiafeng.trade.order.pipline.pipe.AbstractPipe;
import org.apache.commons.lang.StringUtils;

/**
 * 物流校验
 *
 * Created by yaojiafeng on 2019/3/7 11:07 PM.
 */
public class LogisticPipe extends AbstractPipe<OrderForm, String> {

    @Override
    protected void verifyParameter(OrderForm orderForm, ResultData<String> resultData) {
        if (StringUtils.isBlank(orderForm.getLogistic())) {
            resultData.setCode(ErrorEnum.PARAMETER_ERROR.getCode());
            resultData.setCode("物流不能为空");
        }
    }

    @Override
    protected void bizHandler(OrderForm orderForm, ResultData<String> resultData, PipeInnerContext pipeInnerContext) {
        System.out.println(orderForm.getLogistic());
    }

    @Override
    public String name() {
        return PipeEnum.LOGISTIC_PIPE.name();
    }
}
