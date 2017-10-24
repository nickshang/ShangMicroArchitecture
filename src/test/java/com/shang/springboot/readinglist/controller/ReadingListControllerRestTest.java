package com.shang.springboot.readinglist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.shang.springboot.readinglist.entity.Book;


import static org.junit.Assert.*;

/**
 * ReadingListControllerRest单元测试
 * Created by Think on 2017/10/24.
 */
public class ReadingListControllerRestTest {

    // 使用apache http client 访问数据
    @Test
    public void readersBooksByApacheHttp() throws Exception {
        try{
            HttpClient client  = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:8080/book/" + 1);
            request.setHeader("Accept","application/json");

           HttpResponse response =  client.execute(request);
           HttpEntity entity =  response.getEntity();
           ObjectMapper mapper = new ObjectMapper();
           Book book  = mapper.readValue(entity.getContent(),Book.class);               // getContent

           assertEquals((long)book.getId(),1L);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void readersBooks() throws Exception {


    }

    @Test
    public void save() throws Exception {
        // 1.检索资源
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject("http://localhost:8080/book/{id}",Book.class,1);
        assertEquals((long)book.getId(),1L);

        // 2.抽取响应的元数据
        ResponseEntity<Book> responseEntity =  restTemplate.getForEntity("http://localhost:8080/book/{id}",Book.class,1);
        assertEquals( responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void bookNotFound() throws Exception {

    }

}