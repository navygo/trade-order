package com.yaojiafeng.trade.order.common;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaojiafeng on 2019/3/7 6:00 PM.
 */
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "200";

    public static final String FAIL = "500";

    private Map<String, Object> models = new HashMap(4);

    private String message = "";

    private String code = "";

    private T data;

    private long totalCount;

    public ResultData() {
        this.setCode(SUCCESS_CODE);
    }

    public ResultData(String code) {
        this.setCode(code);
    }

    public T getDefaultModel() {
        return this.data;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setDefaultModel(T model) {
        this.data = model;
    }

    public void setModel(String key, String model) {
        this.models.put(key, model);
    }

    public Map<String, Object> getModels() {
        return this.models;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.getCode());
    }

    public String getMessage() {
        return this.message;
    }

    public ResultData setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public ResultData setCode(String code) {
        this.code = code;
        return this;
    }


}
