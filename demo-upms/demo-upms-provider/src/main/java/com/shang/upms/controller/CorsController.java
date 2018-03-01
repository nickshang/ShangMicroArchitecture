package com.shang.upms.controller;

import com.shang.upms.dao.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跨域访问，Spring提供优雅解决方案
 * 参考方案：http://www.imooc.com/article/7719、https://spring.io/guides/gs/rest-service-cors/
 * @author NICK
 * @date 2017-12-11 10:26
 **/
@Controller
public class CorsController {

    @RequestMapping(value="/api/getBook")
    @CrossOrigin(origins = "http://localhost:9000")
    public Book getBook(){
        Book book = new Book();
        book.setTitle("Java多线程并发实践");

        return book;
    }

}
