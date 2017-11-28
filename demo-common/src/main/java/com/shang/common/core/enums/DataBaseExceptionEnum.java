package com.shang.common.core.enums;/**
 * Created by Think on 2017/11/26.
 */


import com.shang.common.core.exception.DaoException;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据库异常枚举定义
 *
 * @author NICK
 * @date 2017-11-26 12:42
 **/
public enum  DataBaseExceptionEnum {


    DB_INSERT_RESULT_0(50010001,"数据库操作,insert返回0"),
    DB_UPDATE_RESULT_0(50010002,"数据库操作,update返回0"),
    DB_SELECTONE_IS_NULL(50010003,"数据库操作,selectOne返回null"),
    DB_LIST_IS_NULL(50010004,"数据库操作,list返回null"),
    DB_GET_SEQ_NEXT_VALUE_ERROR(50010005,"序列生成超时");


    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String msg;


    DataBaseExceptionEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
