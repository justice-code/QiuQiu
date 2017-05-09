package org.eddy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by eddy on 2017/5/9.
 */
public class CglibProxy implements MethodInterceptor {


    public Object createProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        // 设置要代理的目标类，以扩展它的功能
        enhancer.setSuperclass(target.getClass());
        // 设置单一回调对象，在回调中拦截对目标方法的调用
        enhancer.setCallback(this);
        //设置类装载器
        enhancer.setClassLoader(target.getClass().getClassLoader());
        //创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (null != method && StringUtils.startsWith(method.getName(), "set")) {
            System.out.println("can not set");
            return null;
        }
        return methodProxy.invokeSuper(o, objects);
    }
}
