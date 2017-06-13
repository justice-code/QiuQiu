package org.eddy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/6/6.
 */
@Aspect
@Component
@ConditionalOnMissingBean(Point.class)
public class MapperPoint implements Point{

    @Override
    @Pointcut("execution(* org.eddy.dao.mapper..*(..))")
    public void mapperCheck() {}

}
