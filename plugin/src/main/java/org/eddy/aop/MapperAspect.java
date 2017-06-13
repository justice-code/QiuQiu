package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.eddy.sql.config.KeyParam;
import org.eddy.sql.config.RequestHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by eddy on 2017/6/13.
 */
@Aspect
@Component
public class MapperAspect {

    @Around("MapperPoint.mapperCheck()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
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
