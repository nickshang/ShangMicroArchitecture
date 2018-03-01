package com.shang.app.apiVersion;

import java.lang.annotation.*;

/**
 * @author NICK
 * @create 2017-12-25
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiParam {
    /**
     * 参数名
     */
    String value();

    /**
     * 是否必须有值 默认必须有
     */
    boolean required() default true;

    /**
     * 值非必须时 如果未传值 默认值
     */
    DefaultValueEnum defaultValue() default DefaultValueEnum.DEFAULT;
}
