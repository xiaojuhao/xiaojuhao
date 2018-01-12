package com.xjh.support.excel.read;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础类,使用此功能的类需要继承该类
 * 
 * @author ghliu
 */
public abstract class ExcelObj {

    private final static Logger LOG = LoggerFactory.getLogger(ExcelObj.class);

    private String              errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 通过属性名设置属性值
     * 
     * @param name
     * @param value
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void putValue(String name, Object value) {
        Class c = this.getClass();
        Class v = value.getClass();
        try {
            Method m = c.getMethod("set" + name, new Class[] { v });
            m.invoke(this, new Object[] { value });
        } catch (Exception e) {
            LOG.error("ExcelObj:putValue异常：", e);
        }
    }

    /**
     * 返回属性名对应的值
     * 
     * @param name
     * @return 属性名对应的值
     */
    @SuppressWarnings("unchecked")
    public Object outValue(String name) {
        @SuppressWarnings("rawtypes")
        Class c = this.getClass();
        Object o = null;
        try {
            Method m = c.getMethod("get" + name, new Class[] {});
            o = m.invoke(this, new Object[] {});
        } catch (Exception e) {
            LOG.error("ExcelObj:putValue异常：", e);
        }
        return o;
    }
}
