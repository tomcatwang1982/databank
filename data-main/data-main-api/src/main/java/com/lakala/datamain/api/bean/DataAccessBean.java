package com.lakala.datamain.api.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by wangxinyi on 2017/1/30.
 */
public class DataAccessBean implements Serializable {

    /** 业务 **/
    @JsonProperty("product")
    @NotNull
    @Size(min = 6, max = 5)
    private String product;

    /** 模块 **/
    @JsonProperty("module")
    @NotNull
    private String module;

    /** auther **/
    @JsonProperty("author")
    @NotNull
    private String auther;

    /** 用户自定义对象 **/
    @JsonProperty("customerbean")
    private CustomerBean customerBean;

    /** 获取系统对象 包括访问的客户端ip，是浏览器还是移动端访问等 **/
    @JsonProperty("systembean")
    private SystemBean systemBean;

    @JsonProperty("authbean")
    @NotNull
    private AuthBean authBean;

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

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    @Override
    public String toString() {
        return "DataAccessBean{" +
                "product='" + product + '\'' +
                ", module='" + module + '\'' +
                ", auther='" + auther + '\'' +
                ", customerBean=" + customerBean +
                ", systemBean=" + systemBean +
                ", authBean=" + authBean +
                '}';
    }
}
