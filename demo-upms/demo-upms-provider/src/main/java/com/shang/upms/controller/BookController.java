package com.shang.upms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet3 异步化业务
 * @author NICK
 * @create 2018-02-27
 **/
@Controller
public class BookController {

    public void getBook(HttpServletRequest request,
                        @RequestParam(value="skuid") final Long skuid,
                        @RequestParam(value="cat1") final Long cat1,
                        @RequestParam(value="cat2") final Long cat2
                        )throws Exception{

    }
}
