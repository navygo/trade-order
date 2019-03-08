package com.yaojiafeng.trade.order.enums;

/**
 * Created by yaojiafeng on 2019/3/8 12:22 AM.
 */
public enum PipeEnum {

    ADDRESS_PIPE("address"),
    LOGISTIC_PIPE("logistic"),
    ORDER_PIPE("order"),
    STOCK_PIPE("stock");

    PipeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
