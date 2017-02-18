package com.lakala.databus.dataaccess.api.bean;

import java.io.Serializable;

/**
 * Created by wangxinyi on 2017/1/30.
 */
public class DataAccessBean implements Serializable {

    /** 业务 **/
    private String product;

    /** 模块 **/
    private String module;

    /** auther **/
    private String auther;

    /** 用户自定义对象 **/
    private CustomerBean customerBean;

    /** 获取系统对象 包括访问的客户端ip，是浏览器还是移动端访问等 **/
    private SystemBean systemBean;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public CustomerBean getCustomerBean() {
        return customerBean;
    }

    public void setCustomerBean(CustomerBean customerBean) {
        this.customerBean = customerBean;
    }

    public SystemBean getSystemBean() {
        return systemBean;
    }

    public void setSystemBean(SystemBean systemBean) {
        this.systemBean = systemBean;
    }

    @Override
    public String toString() {
        return "DataAccessBean{" +
                "product='" + product + '\'' +
                ", module='" + module + '\'' +
                ", auther='" + auther + '\'' +
                ", customerBean=" + customerBean +
                ", systemBean=" + systemBean +
                '}';
    }
}
