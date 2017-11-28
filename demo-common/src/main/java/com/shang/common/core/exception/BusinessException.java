package com.shang.common.core.exception;

/**
 * 业务处理异常处理
 *
 * @author NICK
 * @date 2017-11-26 12:23
 **/
public class BusinessException extends AppException {

    private static final long serialVersionUID = -5875371379845226068L;

    public BusinessException(int code, String msgFormat, Object... args) {
          super(code,msgFormat,args);
    }

    /**
     * 实例化异常
     * @param msgFormat
     * @param args
     * @return
     */
    private BusinessException newInstance(String msgFormat, Object... args) {
        return new BusinessException(this.code, msgFormat, args);
    }
}
