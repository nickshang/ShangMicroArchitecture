package com.shang.app.controller;

import com.shang.app.apiVersion.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NICK
 * @create 2017-12-25
 **/


@RestController
public class TestApiVersionController {
    @ApiVersion(targetClass = TestApiVersionDo.class)
    @RequestMapping("/api/v1/test")
    public void test() {
        System.out.println("---------------");
    }

    @ApiVersion(targetClass = TestApiVersionDo.class, methodPreName = "test")
    @RequestMapping("/api/v1/testno")
    public void testNo() {
    }
}
