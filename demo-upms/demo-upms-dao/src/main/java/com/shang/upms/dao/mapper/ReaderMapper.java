package com.shang.upms.dao.mapper;


import com.shang.upms.dao.entity.Reader;

/**
 * Created by Think on 2017/6/10.
 */
public interface ReaderMapper {

    // 传递多个参数：1. map； 2.Param
    public Reader findOne(String username);
}
