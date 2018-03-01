package com.shang.app.controller;

import com.shang.app.apiVersion.ApiParam;
import com.shang.app.apiVersion.DefaultValueEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author NICK
 * @create 2017-12-25
 **/


@Component
public class TestApiVersionDo {

    public void test1(){
        System.out.println("test!!!");
    }

    private String test2(){
        System.out.println("test!!!");

        return "调用成功 没有参数";
    }

    public String test3() {
        System.out.println("test!!!");

        return "调用成功 没有参数";
    }

    public String test4(@ApiParam("a") String app) {
        System.out.println("test!!!");

        return "调用成功 一个参数 app:"+app;
    }
    public String test5(@ApiParam("av") Integer av,@ApiParam("a") String app,@ApiParam(value = "b",required = false) String b){
        return "调用成功 三个参数 app:"+app+" av:"+av+" b:"+b;
    }
    public String test6(@ApiParam("amount") BigDecimal amount, @ApiParam(value = "l",required = false) long l, @ApiParam(value = "b",required = false,defaultValue = DefaultValueEnum.TRUE) Boolean b){
        return "调用成功 三个参数 amount:"+amount+" l:"+l+" b:"+b;
    }
}
