package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by creasypita on 8/23/2019.
 */
public class ProxyManager {
    public static <T> T createProxy(final Class<?> targetClass , final List<Proxy> proxyList)
    {
        return (T)Enhancer.create(targetClass, new MethodInterceptor() {
                    public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                        return new ProxyChain(targetObject.getClass(), targetObject,targetMethod, methodProxy, methodParams,proxyList).doProxyChain();
                    }
                }
        );

    }
}
