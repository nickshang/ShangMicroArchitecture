package com.shang.app.apiVersion;

/**
 * @author NICK
 * @create 2017-12-25
 **/

public class ApiVersionException extends RuntimeException {

    public ApiVersionException(String message) {
        super(message);
    }

    public ApiVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
