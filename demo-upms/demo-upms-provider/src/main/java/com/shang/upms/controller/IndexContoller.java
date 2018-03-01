package com.shang.upms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author NICK
 * @date 2017-12-15 14:07
 **/
@Controller
public class IndexContoller {

    //@RequestMapping( value="/")
    public String index(){
        return "index";
    }
}
