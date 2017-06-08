package org.eddy.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by eddy on 2017/6/8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ignore {
}
