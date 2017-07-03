package com.shang.springboot.readinglist.config;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by Think on 2017/6/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties({DataSourceConfig.class})
public class YmlConfigTest {

    @Autowired
    private YmlConfig ymlConfig;

    @Test
    public void getSimpleProp() throws Exception {
        assertThat(ymlConfig.getSimpleProp(),containsString("simplePropValue"));
    }

    @Test
    public void getListProp1() throws Exception {
        List<Map<String, String>> list =   this.ymlConfig.getListProp1();

        assertThat(list.get(0),hasEntry("name","abc"));
        assertThat(list.get(0),hasEntry("value","abcValue"));

        assertThat(list.get(1),hasEntry("name","efg"));
        assertThat(list.get(1),hasEntry("value","efgValue"));
    }

    @Test
    public void getListProp2() throws Exception {
        List<String> list =   this.ymlConfig.getListProp2();

        assertThat(list,hasItem("config2Value1"));
        assertThat(list,hasItem("config2Value2"));
    }

    @Test
    public void getArrayProps() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        assertThat(ymlConfig.getArrayProps(),arrayContaining("1","2","3","4","5"));
    }

    @Test
    public void g() throws Exception {
        Map<String,String> map  = this.ymlConfig.getMapProps();
        assertThat(map,hasEntry("key1","value1"));

    }


}