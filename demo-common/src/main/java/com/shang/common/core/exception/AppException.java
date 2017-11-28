package com.shang.common.core.exception;/**
 * Created by Think on 2017/11/26.
 */

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象异常类
 * 异常架构设计参考：
 *          http://blog.csdn.net/suifeng3051/article/details/52126895
 *          http://blog.csdn.net/ufo2910628/article/details/40399539
 * @author NICK
 * @date 2017-11-26 12:30
 **/
public abstract class AppException extends  RuntimeException{

    /**
     * 具体异常码
     */
    @Setter
    @Getter
    protected int code;

    /**
     * 异常信息
     */
    @Setter
    @Getter
    protected String msg;


    public AppException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }



}
