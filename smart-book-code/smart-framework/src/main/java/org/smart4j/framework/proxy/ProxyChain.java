package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by creasypita on 8/23/2019.
 */
public class ProxyChain {

    private Class<?> targetClass;
    private Object targetObject;
    private Method targetMethod;
    private MethodProxy methodProxy;
    private Object[] methodParams;

    private List<Proxy> proxyList;
    private int proxyIndex;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }


    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult ;
        if(proxyIndex < proxyList.size())
        {
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }
        else
        {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
