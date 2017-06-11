package com.shang.springboot.readinglist.mapper;


import com.shang.springboot.readinglist.entity.Book;
import com.shang.springboot.readinglist.entity.Reader;

import java.util.List;

/**
 * Created by Think on 2017/6/10.
 */
public interface BookMapper {

    public Book selectById(long id);

    public List<Book> findByReader(Reader reader);

    public void save(Book book);
}
