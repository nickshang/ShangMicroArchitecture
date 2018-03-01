package com.shang.upms.controller;

import com.alibaba.fastjson.JSON;
import com.shang.upms.dao.entity.Book;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JSONP实例
 * 需要配合：CorsFilter
 * @author NICK
 * @date 2017-12-10 21:22
 **/
@Controller
public class JsonpController {

    @RequestMapping(method = RequestMethod.GET, value = "/read", params = "callback")
    public void getReader(HttpServletResponse response, @RequestParam("callback") String callback) throws IOException {

        response.setContentType("application/javascript;charset=utf-8");

        Book book = new Book();
        book.setTitle("Javascript 高级程序设计");

        MappingJacksonValue value = new MappingJacksonValue(book);
        value.setJsonpFunction(callback);

        PrintWriter pw = response.getWriter();
        pw.write(callback + "(" + JSON.toJSONString(book) + ")");
        pw.flush();
        pw.close();

    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("Javascript 高级程序设计");
        MappingJacksonValue value = new MappingJacksonValue(book);
        value.setJsonpFunction("cal");

        System.out.println(JSON.toJSONString(value.getValue()));

    }
}


