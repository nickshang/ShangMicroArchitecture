package com.shang.springboot.readinglist.repository;

import com.shang.springboot.readinglist.entity.Reader;
import com.shang.springboot.readinglist.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Think on 2017/6/10.
 */
@Component
public class ReaderRepositoryImpl implements ReaderRepository {

    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public UserDetails findOne(String username) {
        Reader read =  readerMapper.findOne(username);

        Reader reader = new Reader();
        reader.setId(read.getId());
        reader.setFullname(read.getFullname());
        reader.setUsername(read.getUsername());
        reader.setPassword(read.getPassword());

        return reader;
    }
}
