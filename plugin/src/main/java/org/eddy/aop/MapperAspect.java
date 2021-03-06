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

    @Around("mapperCheckPoint()")
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
        String[] ognl = keyParam.value();
        String mapper = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        RequestHolder.initRequestHolder(mapper, ognl);

        Object result = point.proceed();

        RequestHolder.resetRequest();
        return result;
    }

    //************************抽象方法**************************

    /**
     * 切入点
     */
    public abstract void mapperCheckPoint();

    /**
     * 自定义校验是否需要进行sql重写
     * @param point
     * @return true：需要进行sql重写
     */
    protected abstract boolean check(ProceedingJoinPoint point);

}
