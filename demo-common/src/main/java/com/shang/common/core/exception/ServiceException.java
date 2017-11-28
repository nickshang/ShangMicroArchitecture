package com.shang.common.core.exception;/**
 * Created by Think on 2017/11/26.
 */

/**
 * 服务器异常类
 * @author NICK
 * @date 2017-11-26 16:32
 **/
public class ServiceException extends AppException  {


    public ServiceException(int code, String msgFormat, Object... args) {
        super(code,msgFormat,args);
    }


    /**
     * 实例化异常
     * @param msgFormat
     * @param args
     * @return
     */
    private ServiceException newInstance(String msgFormat, Object... args) {
        return new ServiceException(this.code, msgFormat, args);
    }



}
