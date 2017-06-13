package org.eddy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/6/6.
 */
@Aspect
@Component
public class MapperPoint {


    @Pointcut("execution(* org.eddy.dao.mapper..*(..))")
    public void mapperCheck() {}

}
