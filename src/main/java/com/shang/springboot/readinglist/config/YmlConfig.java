package com.shang.springboot.readinglist.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义属性配置
 * Created by Think on 2017/6/30.
 */
@ConfigurationProperties(prefix="myProps") //application.yml中的myProps下的属性
@Data
public class YmlConfig {
    private String simpleProp;
    private String[] arrayProps;
    private List<Map<String, String>> listProp1 = new ArrayList<>();          //接收prop1里面的属性值
    private List<String> listProp2 = new ArrayList<>();                       //接收prop2里面的属性值
    private Map<String, String> mapProps = new HashMap<>();                   //接收prop1里面的属性值
}