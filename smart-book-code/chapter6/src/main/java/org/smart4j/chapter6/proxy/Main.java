package org.smart4j.chapter6.proxy;

import org.smart4j.chapter6.service.GreetingImpl;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class Main {
    public static void main(String args[]) {
        GreetingProxy proxy = new GreetingProxy(new GreetingImpl());
        proxy.sayHello();
    }
}
