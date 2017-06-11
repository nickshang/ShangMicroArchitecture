package com.shang.springboot.readinglist.repository;

import com.shang.springboot.readinglist.entity.Book;
import com.shang.springboot.readinglist.entity.Reader;

import java.util.List;

/**
 * Created by Think on 2017/6/9.
 */
public interface ReadingListRepository {
    public List<Book> findByReader(Reader reader);
    public void save(Book book);

}
