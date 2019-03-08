package com.yaojiafeng.trade.order.enums;

/**
 * Created by yaojiafeng on 2019/3/7 11:25 PM.
 */
public enum EngineExecutorEnum {

    ORDER_ENGINE_EXECUTOR("orderEngineExecutor", "下单执行引擎"),
    VIRTUAL_ORDER_ENGINE_EXECUTOR("virtualOrderEngineExecutor", "虚拟商品下单执行引擎");

    private String code;

    private String desc;

    EngineExecutorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
