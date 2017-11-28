package com.shang.upms.service.exceptionhandle;

import lombok.Getter;

/**
 * Created by Think on 2017/10/24.
 */
public class BookNotFoundException extends  RuntimeException{

    @Getter
    private long id;

    public BookNotFoundException(long id){
        this.id = id;
    }
}
