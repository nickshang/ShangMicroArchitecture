package com.shang.springboot.readinglist.controller;

import com.shang.springboot.DemoApplication;
import com.shang.springboot.readinglist.AmazonProperties;
import com.shang.springboot.readinglist.config.DataSourceConfig;
import com.shang.springboot.readinglist.entity.Reader;
import com.shang.springboot.readinglist.repository.ReadingListRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ReadingListController  单元测试（模拟Spring MVC）
 * Created by Think on 2017/6/30.
 */
// 单元测试第一种注释方案
@RunWith(SpringRunner.class)
@SpringBootTest(  classes = DemoApplication.class)
@EnableConfigurationProperties({AmazonProperties.class,DataSourceConfig.class})

// 单元测试第二种注释方案
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoApplication.class)  // 开启Web上下文 测试
//@EnableConfigurationProperties({AmazonProperties.class,DataSourceConfig.class})
//@WebAppConfiguration
//@WebMvcTest(controllers = ReadingListController.class)
public class ReadingListControllerTest {

    private MockMvc mockMvc;

    //注入WebApplicationContext
    @Autowired
    private WebApplicationContext webContext;

    @MockBean
    private ReadingListRepository readingListRepository;

    @Autowired
    private AmazonProperties amazonProperties;

    @Autowired
    private  DataSourceConfig dataSourceConfig;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @WithMockUser(username="NICK",password="123456",roles="READER")
    public void homePage() throws Exception {

        Reader reader = new Reader();
        reader.setFullname("NICK");
        reader.setId(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/readingList").session((MockHttpSession)getLoginSession()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readingList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books",
                        Matchers.is(Matchers.empty())));

    }

    @Test
    public void test()  throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id","1")
        .param("isbn","xxxxxxxxxxxxxxxx")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("ok"));
    }

    @Test
    public void testamazonProperties(){
        assertEquals(amazonProperties.getAssociateId(),"habuma-20");
    }


    @Test
    public void testDataSourceConfig(){
        assertEquals(dataSourceConfig.getMaxActive(),5);
    }
    /**
     * 获取登入信息session
     * @return
     * @throws Exception
     */
    @WithMockUser(username="NICK",password="123456",roles="READER")
    private HttpSession getLoginSession() throws Exception{
        // mock request get login session
        // url = /xxx/xxx/{username}/{password}
        MvcResult result = this.mockMvc
                .perform((get("/login")
                        .param("username","NICK")
                        .param("password","123456")))
                .andExpect(status().isOk())
                .andReturn();
        return result.getRequest().getSession();
    }



    @Test
    public void readersBooks() throws Exception {
    }

    @Test
    public void addToReadingList() throws Exception {
    }

}