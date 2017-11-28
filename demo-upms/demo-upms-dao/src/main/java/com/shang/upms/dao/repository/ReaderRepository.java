package com.shang.upms.dao.repository;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Think on 2017/6/9.
 */
public interface ReaderRepository {
    public UserDetails findOne(String username);
}