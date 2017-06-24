package org.eddy.sql.config;

import java.lang.annotation.*;

/**
 * Created by eddy on 2017/6/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KeyParam {

    /**
     * ognl 表达式
     * @return ognl 表达式
     */
    String[] value();
}
