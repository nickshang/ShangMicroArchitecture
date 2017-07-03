package com.shang.springboot.readinglist.mapper;

import com.shang.springboot.DemoApplication;
import com.shang.springboot.readinglist.config.DataSourceConfig;
import com.shang.springboot.readinglist.entity.Reader;
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
@SpringBootTest(classes = DemoApplication.class)
@EnableConfigurationProperties({DataSourceConfig.class})
public class ReaderMapperIntegTest {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void findOne() throws Exception {
        Reader reader = this.readerMapper.findOne("NICK");
        Assert.assertEquals(reader.getFullname(),"SHANG");
        Assert.assertEquals(reader.getPassword(),"123456");
    }

}