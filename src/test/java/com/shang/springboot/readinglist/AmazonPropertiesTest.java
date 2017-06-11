package com.shang.springboot.readinglist;

import com.shang.springboot.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Think on 2017/6/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes=DemoApplication.class)
public class AmazonPropertiesTest {

    @Autowired
    private AmazonProperties amazonProperties;

    @Test
    public void getAssociateId() throws Exception {
        Assert.assertEquals(amazonProperties.getAssociateId(),"habuma-20");
    }

}