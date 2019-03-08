package com.yaojiafeng.trade.order.dto;

import java.util.List;

/**
 * Created by yaojiafeng on 2019/3/7 6:03 PM.
 */
public class OrderForm {

    private String address;

    private String logistic;

    private List<ItemDTO> itemDTOList;

    private String orderEngineExecutor;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogistic() {
        return logistic;
    }

    public void setLogistic(String logistic) {
        this.logistic = logistic;
    }

    public List<ItemDTO> getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(List<ItemDTO> itemDTOList) {
        this.itemDTOList = itemDTOList;
    }

    public String getOrderEngineExecutor() {
        return orderEngineExecutor;
    }

    public void setOrderEngineExecutor(String orderEngineExecutor) {
        this.orderEngineExecutor = orderEngineExecutor;
    }
}
