package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/6/14.
 */
@Aspect
@Component
public class MineMapperAspect extends MapperAspect {

    @Pointcut("execution(* org.eddy.dao.mapper..*(..))")
    public void mapperCheck() {}

    /**
     * 校验是否需要进行sql重写
     *
     * @param point
     * @return true：需要进行sql重写
     */
    @Override
    protected boolean check(ProceedingJoinPoint point) {
        return true;
    }
}
