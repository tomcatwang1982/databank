package com.lakala.datamain.provider;

import com.lakala.datamain.api.bean.DataAccessBean;
import com.lakala.datamain.api.service.DataAccessService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Created by wangxinyi on 2017/2/20.
 */
public class DataAccessServiceImpl implements DataAccessService {

    @GET
    @Path("access")
    @Produces("application/json; charset=utf-8")
    public String dataAccess(DataAccessBean dataAccessBean) {
        return "hello world";
    }
}
