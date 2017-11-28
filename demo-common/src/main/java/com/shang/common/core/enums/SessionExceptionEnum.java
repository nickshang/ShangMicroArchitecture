package com.shang.common.core.enums;/**
 * Created by Think on 2017/11/26.
 */

import lombok.Getter;
import lombok.Setter;

/**
 * 会话异常枚举定义
 *
 * @author NICK
 * @date 2017-11-26 13:12
 **/
public enum  SessionExceptionEnum {

    SESSION_EXPIRED(10010001,"会话超时"),
    TOKEN_IS_ILLICIT(10010002,"验证码错误");

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String msg;

    SessionExceptionEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
