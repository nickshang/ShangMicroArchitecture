package com.shang.upms.dao.entity;

import lombok.*;

/**
 * 书籍实体
 * Created by Think on 2017/6/9.
 */
@Data
@ToString
public class Book {

    private Long id;
    private Reader reader;
    private String isbn;
    private String title;
    private String author;
    private String description;
}
