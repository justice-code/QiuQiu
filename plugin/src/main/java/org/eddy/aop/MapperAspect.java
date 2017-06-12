package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/6/6.
 */
@Aspect
@Component
public class MapperAspect {

    @Pointcut("execution(* org.eddy.dao.mapper..*(..)) && (@annotation(org.eddy.sql.config.KeyParam))")
    public void mapperCheck() {}

    @Around("mapperCheck()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        System.out.println("into");
        return point.proceed();
    }
}
