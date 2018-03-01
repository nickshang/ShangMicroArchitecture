package com.shang.upms.controller;

import com.shang.upms.service.AysncService;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * 异步加载
 * @author NICK
 * @create 2018-01-10
 **/
@Controller
public class AsyncController {

    @Autowired
    AysncService aysncService;


    @RequestMapping("/call")
    @ResponseBody
    public Callable<String> asyncCall() {
        // 借助mvcTaskExecutor在另外一个线程调用
        // 此时Servlet容器线程已经释放,可以处理其他的请求
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Async Hello World";
            }
        };
    }

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        // 调用aysncService的getAsyncUpdate方法
        // deferredResult被计划任务每五秒钟更新一次
        return aysncService.getAsyncUpdate();
    }

}
