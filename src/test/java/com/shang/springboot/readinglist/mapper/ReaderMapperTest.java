package com.shang.springboot.readinglist.mapper;

import com.shang.springboot.DemoApplication;
import com.shang.springboot.readinglist.entity.Reader;
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
public class ReaderMapperTest {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void findOne() throws Exception {
        Reader reader = this.readerMapper.findOne("NICK");
        Assert.assertEquals(reader.getFullname(),"SHANG");
        Assert.assertEquals(reader.getPassword(),"123456");
    }

}