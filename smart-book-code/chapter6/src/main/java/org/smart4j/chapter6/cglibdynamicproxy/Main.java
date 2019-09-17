package org.smart4j.chapter6.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import org.smart4j.chapter6.service.Greeting;
import org.smart4j.chapter6.service.GreetingImpl;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class Main {
    public static void main(String[] args) {
        // 1
//        Greeting greetingProxy = (Greeting) Enhancer.create(GreetingImpl.class, new DynamicProxy());
//        greetingProxy.sayHello();

        // 2
//        new DynamicProxy().getProxy(GreetingImpl.class).sayHello();

        //3
        DynamicProxy.getInstance().getProxy(GreetingImpl.class).sayHello();
    }
}
