package com.lakala.databus.dataaccess.api.extension;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 调用权限校验
 * Created by wangxinyi on 2017/1/30.
 */
public class AuthInterceptor implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
        String token = request.getParameter("token");
        String ip = request.getLocalAddr();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean flag = isAuth(token, user, pass, ip);

        if (flag) {
            return invoker.invoke(invocation);
        } else {
//            AppDomain app=new AppDomain();
//            app.setMessage("900");
//            app.setData(null);
//            print(JsonUtil.toJson(app),response);
            return new RpcResult();
        }
    }

    private boolean isAuth(String token, String user, String pass, String ip) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((user + pass + ip).getBytes());
            String tokenMd5String = new BigInteger(1, md.digest()).toString(16);
            if (null != tokenMd5String && !tokenMd5String.equals("") && tokenMd5String.equals(token))
                return true;
            else
                return false;
        } catch (Exception e) {
            LOGGER.error("data access 访问权限校验异常！",e);
            return false;
        }
    }
}
