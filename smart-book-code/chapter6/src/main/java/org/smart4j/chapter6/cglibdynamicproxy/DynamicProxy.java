package org.smart4j.chapter6.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class DynamicProxy implements MethodInterceptor {

    private static DynamicProxy instance = new DynamicProxy();

    private DynamicProxy(){}

    public static DynamicProxy getInstance() {
        return instance;
    }

    public <T> T getProxy(Class<T> targetClass) {
        return (T)Enhancer.create(targetClass, instance);
    }


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public void before() {
        System.out.println("log on  will be invoke");
    }

    public void after() {
        System.out.println("log on sayhello end invoke");
    }
}
