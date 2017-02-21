package com.lakala.datamain.api.service;

import com.lakala.datamain.api.bean.DataAccessBean;
import com.lakala.datamain.api.bean.DataAccessResultBean;

/**
 * Created by mujinhua on 2017/2/20.
 */
public interface DataAccessService {

    DataAccessResultBean dataAccess(Long id);

    DataAccessResultBean dataAccess(DataAccessBean dataAccessBean);
}
