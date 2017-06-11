package com.shang.springboot.readinglist.repository;

import com.shang.springboot.readinglist.entity.Book;
import com.shang.springboot.readinglist.entity.Reader;
import com.shang.springboot.readinglist.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Think on 2017/6/10.
 */
@Component
public class ReadingListRepositoryImpl implements  ReadingListRepository {
    @Autowired
    private  BookMapper bookMapper;

    @Override
    public List<Book> findByReader(Reader reader) {
        return bookMapper.findByReader(reader);
    }

    @Override
    public void save(Book book) {
        this.bookMapper.save(book);
    }
}
