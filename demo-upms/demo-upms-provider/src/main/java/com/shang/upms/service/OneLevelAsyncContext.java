package com.shang.upms.service;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 业务线程池封装
 * @author NICK
 * @create 2018-02-27
 **/

public class OneLevelAsyncContext {
    public void submitFuture(
            final HttpServletRequest req,
            final Callable<Object> task
            ){

        final String uri = req.getRequestURI();
        final Map<String,String[]> params = req.getParameterMap();

        // 开启异步上下文
        final AsyncContext asyncContext = req.getAsyncContext();
        asyncContext.getRequest().setAttribute("uri",uri);
        asyncContext.getRequest().setAttribute("params",params);
        asyncContext.setTimeout(1000);

        //






















    }
}
