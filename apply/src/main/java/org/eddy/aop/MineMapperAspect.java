package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/6/14.
 */
@Aspect
@Component
public class MineMapperAspect extends MapperAspect {


    /**
     * 切入点
     */
    @Override
    @Pointcut("execution(* org.eddy.dao.mapper..*(..))")
    public void mapperCheckPoint() {

    }

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
