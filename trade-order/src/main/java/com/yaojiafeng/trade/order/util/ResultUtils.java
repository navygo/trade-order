package com.yaojiafeng.trade.order.util;

import com.yaojiafeng.trade.order.common.ResultData;
import com.yaojiafeng.trade.order.enums.ErrorEnum;

/**
 * Created by yaojiafeng on 2019/3/7 10:22 PM.
 */
public class ResultUtils {

    public static ResultData build(ErrorEnum errorEnum, ResultData resultData) {
        if (resultData == null) {
            return null;
        }
        resultData.setCode(errorEnum.getCode());
        resultData.setMessage(errorEnum.getMsg());
        return resultData;
    }

}
