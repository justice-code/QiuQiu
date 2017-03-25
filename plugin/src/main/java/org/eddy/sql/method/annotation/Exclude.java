package org.eddy.sql.method.annotation;

import java.lang.annotation.*;

/**
 * Created by eddy on 2017/3/25.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Exclude {
}
