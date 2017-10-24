package com.shang.springboot.readinglist.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Created by Think on 2017/10/24.
 */
@AllArgsConstructor
public class Error {


    @Setter
    @Getter
    private long code;

    @Setter
    @Getter
    private String message;
}
