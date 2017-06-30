package com.shang.springboot.readinglist.mapper;

import com.shang.springboot.readinglist.entity.Reader;

/**
 * Created by Think on 2017/6/10.
 */
public interface ReaderMapper {

    // 传递多个参数：1. map； 2.Param
    public Reader findOne(String username);
}
