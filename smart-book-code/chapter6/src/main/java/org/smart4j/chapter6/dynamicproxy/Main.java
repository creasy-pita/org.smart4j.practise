package org.smart4j.chapter6.dynamicproxy;

import org.smart4j.chapter6.service.Greeting;
import org.smart4j.chapter6.service.GreetingImpl;

import java.lang.reflect.Proxy;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class Main {
    public static void main(String[] args) {
//        Greeting greeting = new GreetingImpl();
//        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(greeting);
//        Greeting greetingProxy = (Greeting) Proxy.newProxyInstance(
//            greeting.getClass().getClassLoader()
//            , greeting.getClass().getInterfaces()
//            , jdkDynamicProxy
//        );
//        greetingProxy.sayHello();
        Greeting greetingProxy = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        greetingProxy.sayHello();

    }
}
