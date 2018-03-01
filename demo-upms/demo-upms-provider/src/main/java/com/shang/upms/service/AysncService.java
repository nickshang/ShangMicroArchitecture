package com.shang.upms.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 异步服务
 * @author NICK
 * @create 2018-01-10
 **/
@Service
public class AysncService {

    private DeferredResult<String> deferredResult;

    public DeferredResult<String> getAsyncUpdate() {

        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis())
                    .toString());
        }
    }



}
