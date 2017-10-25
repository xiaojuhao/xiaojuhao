package com.xjh.commons;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 服务返回结果基础类
 * 
 * @author ghliu 20161117
 * @param <T>
 */
public class ResultBase<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7777729780722369667L;

    private boolean           isSuccess        = false;
    private String            message;
    private String            code;
    private T                 value;

    public ResultBase() {
        super();
    }

    public ResultBase(T value) {
        super();
        this.isSuccess = true;
        this.value = value;
    }
    public ResultBase(boolean success, String message, String code) {
        super();
        this.isSuccess = success;
        this.message = message;
        this.code = code;
    }
    public T getValue() {
        return this.value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
