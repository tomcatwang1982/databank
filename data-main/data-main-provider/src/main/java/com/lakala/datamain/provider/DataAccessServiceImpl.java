package com.lakala.datamain.provider;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.lakala.datamain.api.bean.DataAccessBean;
import com.lakala.datamain.api.bean.DataAccessResultBean;
import com.lakala.datamain.api.service.DataAccessService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;


/**
 * Created by wangxinyi on 2017/2/20.
 */
public class DataAccessServiceImpl implements DataAccessService {

    public DataAccessResultBean dataAccess(Long id) {
        DataAccessResultBean bean = new DataAccessResultBean();
        bean.setMsg("successful");
        bean.setData(id.toString());
        return bean;
    }

    @Override
    public DataAccessResultBean dataAccess(DataAccessBean dataAccessBean) {
        DataAccessResultBean bean = new DataAccessResultBean();
        bean.setMsg("successful");
        bean.setData("xxxxxxxxxxxxxxx");
        System.out.println("============================1");
        return bean;
    }


}
