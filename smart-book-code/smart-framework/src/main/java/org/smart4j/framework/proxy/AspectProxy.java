package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

/**
 * Created by creasypita on 8/23/2019.
 */
public abstract class AspectProxy implements Proxy {
    @Override
    public final Object doProxy(ProxyChain proxyChain) throws  Throwable{
        Object result = null;
        Class<?> targetClass = proxyChain.getTargetClass();
        Object targetObject = proxyChain.getTargetObject();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        try {
            if(intercept(targetClass, targetMethod, methodParams)){
                before(targetClass, targetMethod, methodParams);
                result = proxyChain.doProxyChain();
                after(targetClass, targetMethod, methodParams,result);
            }
            else {
                result = proxyChain.doProxyChain();
            }
        } catch (Throwable e) {
            error(targetClass, targetMethod, methodParams, e);
            throw  e;
        }
        finally {
            end();
        }

        return result;
    }

    public void begin() {
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    public void end() {
    }
}
