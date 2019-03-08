package com.yaojiafeng.trade.order.pipline.pipe.impl;

import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.dto.ItemDTO;
import com.yaojiafeng.trade.order.dto.OrderForm;
import com.yaojiafeng.trade.order.enums.ErrorEnum;
import com.yaojiafeng.trade.order.enums.PipeEnum;
import com.yaojiafeng.trade.order.pipline.InvocationChain;
import com.yaojiafeng.trade.order.pipline.PipeInnerContext;
import com.yaojiafeng.trade.order.pipline.RollBack;
import com.yaojiafeng.trade.order.pipline.pipe.AbstractPipe;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 比如实物商品需要校验库存
 * <p>
 * Created by yaojiafeng on 2019/3/8 12:33 AM.
 */
public class StockPipe extends AbstractPipe<OrderForm, String> implements RollBack<OrderForm, String> {

    private Map<Long, Integer> stockMap = new HashMap<>();

    {
        stockMap.put(1L, 1);
    }

    @Override
    protected void verifyParameter(OrderForm orderForm, ResultData<String> resultData) {
        if (CollectionUtils.isEmpty(orderForm.getItemDTOList())) {
            resultData.setCode(ErrorEnum.PARAMETER_ERROR.getCode());
            resultData.setCode("商品不能为空");
        }
        for (ItemDTO itemDTO : orderForm.getItemDTOList()) {
            if (stockMap.get(itemDTO.getItemId()) == null) {
                resultData.setCode(ErrorEnum.PARAMETER_ERROR.getCode());
                resultData.setCode("库存不够");
            }
        }
    }

    @Override
    protected void bizHandler(OrderForm orderForm, ResultData<String> resultData, PipeInnerContext pipeInnerContext) {
        System.out.println("锁库存");
//        throw new RuntimeException("锁库存失败");
    }

    @Override
    public String name() {
        return PipeEnum.STOCK_PIPE.getName();
    }

    @Override
    public void rollBack(InvocationChain<OrderForm, String> invocationChain) {
        System.err.println("释放库存");
    }
}
