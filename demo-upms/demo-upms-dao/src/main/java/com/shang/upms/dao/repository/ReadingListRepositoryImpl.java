package com.shang.upms.dao.repository;

import com.shang.upms.dao.entity.Book;
import com.shang.upms.dao.entity.Reader;
import com.shang.upms.dao.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by Think on 2017/6/10.
 */
@Component
public class ReadingListRepositoryImpl implements  ReadingListRepository {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findByReader(Reader reader) {
        return bookMapper.findByReader(reader);
    }

    @Override
    public void save(Book book) {
        this.bookMapper.save(book);
    }
}
