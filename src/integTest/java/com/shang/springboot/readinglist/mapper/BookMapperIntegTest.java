package com.shang.springboot.readinglist.mapper;

import com.shang.springboot.DemoApplication;
import com.shang.springboot.readinglist.AmazonProperties;
import com.shang.springboot.readinglist.config.DataSourceConfig;
import com.shang.springboot.readinglist.config.DruidConfig;
import com.shang.springboot.readinglist.config.SessionFactoryConfig;
import com.shang.springboot.readinglist.entity.Book;
import com.shang.springboot.readinglist.entity.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Think on 2017/6/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@EnableConfigurationProperties({DataSourceConfig.class})
public class BookMapperIntegTest {

    @Autowired
    private  DataSourceConfig dataSourceConfig;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void selectById() throws Exception {
        Book book = bookMapper.selectById(1);
        Assert.assertEquals((long)book.getId(),1L);
    }

    @Test
    public void findByReader() throws Exception {

        Reader reader = new Reader();
        reader.setId(1);

        List<Book> books = bookMapper.findByReader(reader);
        Assert.assertTrue(books.size() > 1);
    }

    @Test
    public void save() throws Exception{
        Reader reader = new Reader();
        reader.setId(1);

        Book book = new Book();
        book.setReader(reader);
        book.setIsbn("ISBN:2009100");
        book.setAuthor("NICK");
        book.setTitle("Title:2201");

        this.bookMapper.save(book);
        Assert.assertTrue( book.getId() > 1);

    }

}