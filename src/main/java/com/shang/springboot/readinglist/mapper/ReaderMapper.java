package com.shang.springboot.readinglist.mapper;

import com.shang.springboot.readinglist.entity.Reader;

/**
 * Created by Think on 2017/6/10.
 */
public interface ReaderMapper {

    public Reader findOne(String username);
}
