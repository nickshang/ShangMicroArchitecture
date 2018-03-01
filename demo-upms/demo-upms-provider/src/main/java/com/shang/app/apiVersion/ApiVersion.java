package com.shang.app.apiVersion;

import java.lang.annotation.*;

/**
 * @author NICK
 * @create 2017-12-25
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 指定执行操作的类名
     */
    Class targetClass();

    /**
     * 指定执行操作的方法名前缀
     */
    String methodPreName() default "";

}
