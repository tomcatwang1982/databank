package com.lakala.test;

import com.lakala.common.HttpClientUtil;
import com.lakala.datamain.api.bean.AuthBean;
import com.lakala.datamain.api.bean.CustomerBean;
import com.lakala.datamain.api.bean.DataAccessBean;
import com.lakala.datamain.api.bean.SystemBean;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mujinhua on 2017/2/21.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        List<NameValuePair> lst = new ArrayList<NameValuePair>();
        DataAccessBean dataAccessBean = new DataAccessBean();
        dataAccessBean.setProduct("credit");
        dataAccessBean.setModule("net");
        dataAccessBean.setAuther("test");
        System.out.println("-----------------------------------");
        CustomerBean customerBean = new CustomerBean();
        dataAccessBean.setCustomerBean(customerBean);

        AuthBean authBean = new AuthBean();
        authBean.setIp("127.0.0.1");
        authBean.setPasswd("123456");
        authBean.setToken("xxxxxxxxxxxxxxxxxxxxx");
        authBean.setUser("test");

        dataAccessBean.setAuthBean(authBean);
        SystemBean systemBean = new SystemBean();
        systemBean.setIp("127.0.0.1");
        systemBean.setAccessType("1");
        dataAccessBean.setSystemBean(systemBean);

        NameValuePair nameValuePair = new BasicNameValuePair("accessBean","");
        lst.add(nameValuePair);
        String results  = HttpClientUtil.httpPost("http://localhost:8888/services/users/access",lst);
        System.out.println(results);
    }

}
