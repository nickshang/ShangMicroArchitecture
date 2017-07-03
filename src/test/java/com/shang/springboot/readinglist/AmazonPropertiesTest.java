package com.shang.springboot.readinglist;

import com.shang.springboot.DemoApplication;
import com.shang.springboot.readinglist.config.DataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Think on 2017/6/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(  classes = DemoApplication.class)
@EnableConfigurationProperties({AmazonProperties.class,DataSourceConfig.class})
public class AmazonPropertiesTest {

    @Autowired
    private AmazonProperties amazonProperties;

    @Test
    public void getAssociateId() throws Exception {
        Assert.assertEquals(amazonProperties.getAssociateId(),"habuma-20");
    }

}