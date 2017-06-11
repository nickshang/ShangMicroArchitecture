package com.shang.springboot.readinglist.entity;

import lombok.*;

/**
 * 书籍实体
 * Created by Think on 2017/6/9.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Reader reader;

    @Setter
    @Getter
    private String isbn;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String author;

    @Setter
    @Getter
    private String description;


}
