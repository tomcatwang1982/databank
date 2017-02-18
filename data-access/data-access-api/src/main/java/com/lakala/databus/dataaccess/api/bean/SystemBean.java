package com.lakala.databus.dataaccess.api.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/30.
 */
public class SystemBean implements Serializable {

    /** 客户端ip **/
    private String ip;
    /** 访问方式 app/browser **/
    private String accessType;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "SystemBean{" +
                "ip='" + ip + '\'' +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
