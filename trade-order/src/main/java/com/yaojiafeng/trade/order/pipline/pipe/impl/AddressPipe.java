package com.yaojiafeng.trade.order.pipline.pipe.impl;

import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.enums.ErrorEnum;
import com.yaojiafeng.trade.order.enums.PipeEnum;
import com.yaojiafeng.trade.order.pipline.PipeInnerContext;
import com.yaojiafeng.trade.order.pipline.pipe.AbstractPipe;
import org.apache.commons.lang.StringUtils;

/**
 * 收货地址
 *
 * Created by yaojiafeng on 2019/3/7 11:08 PM.
 */
public class AddressPipe extends AbstractPipe<OrderForm, String> {

    @Override
    protected void verifyParameter(OrderForm orderForm, ResultData<String> resultData) {
        if (StringUtils.isBlank(orderForm.getAddress())) {
            resultData.setCode(ErrorEnum.PARAMETER_ERROR.getCode());
            resultData.setCode("地址不能为空");
        }
    }

    @Override
    protected void bizHandler(OrderForm orderForm, ResultData<String> resultData, PipeInnerContext pipeInnerContext) {
        System.out.println(orderForm.getAddress());
    }

    @Override
    public String name() {
        return PipeEnum.ADDRESS_PIPE.name();
    }
}
