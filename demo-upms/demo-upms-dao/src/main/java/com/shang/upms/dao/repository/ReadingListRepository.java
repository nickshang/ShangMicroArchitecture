package com.shang.upms.dao.repository;


import com.shang.upms.dao.entity.Book;
import com.shang.upms.dao.entity.Reader;

import java.util.List;

/**
 * Created by Think on 2017/6/9.
 */
public interface ReadingListRepository {
    public List<Book> findByReader(Reader reader);
    public void save(Book book);

}
