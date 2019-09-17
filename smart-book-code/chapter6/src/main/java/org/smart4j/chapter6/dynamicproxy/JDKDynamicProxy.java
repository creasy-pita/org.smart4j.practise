package org.smart4j.chapter6.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     *
     * @param <T> 会自动转化为返回值的接收类型  比如  Greeting greetingProxy = this.getProxy();
     * @return
     */
    public <T> T getProxy() {
        return (T)Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    /**
     *
     * @param proxy  proxy = Proxy.newProxyInstance
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object  result = method.invoke(target,args);
        after();
        return result;
    }

    public void before() {
        System.out.println("sayhello will be invoke");
    }

    public void after() {
        System.out.println("sayhello end invoke");
    }
}
