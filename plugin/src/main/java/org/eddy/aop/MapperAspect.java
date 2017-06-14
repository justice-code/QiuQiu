package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.eddy.sql.config.KeyParam;
import org.eddy.sql.config.RequestHolder;

import java.lang.reflect.Method;

/**
 * Created by eddy on 2017/6/14.
 */
public abstract class MapperAspect {

    /**
     * 校验是否需要进行sql重写
     * @param point
     * @return true：需要进行sql重写
     */
    protected abstract boolean check(ProceedingJoinPoint point);

    @Around("mapperCheck()")
    private Object around(ProceedingJoinPoint point) throws Throwable {

        if(! check(point)) {
            return point.proceed();
        }
        if (! (point.getSignature() instanceof MethodSignature)) {
            return point.proceed();
        }

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        if (! method.isAnnotationPresent(KeyParam.class)) {
            return point.proceed();
        }

        KeyParam keyParam = method.getAnnotation(KeyParam.class);
        String ognl = keyParam.value();
        RequestHolder.initRequestHolder(ognl);

        Object result = point.proceed();

        RequestHolder.resetRequest();
        return result;
    }
}
