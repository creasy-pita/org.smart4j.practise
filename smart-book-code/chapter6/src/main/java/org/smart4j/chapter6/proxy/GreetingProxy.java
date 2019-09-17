package org.smart4j.chapter6.proxy;

import org.smart4j.chapter6.service.Greeting;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class GreetingProxy implements Greeting {

    private Greeting greeting;

    public GreetingProxy(Greeting greeting) {
        this.greeting = greeting;
    }

    public void sayHello() {
        before();
        greeting.sayHello();
        after();
    }

    public void before() {
        System.out.println("sayhello will be invoke");
    }

    public void after() {
        System.out.println("sayhello end invoke");
    }
}
