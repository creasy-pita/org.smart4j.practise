package org.smart4j.chapter6.service;


import net.sf.cglib.proxy.Enhancer;

/**
 * Created by creasypita on 9/17/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class GreetingImpl implements Greeting {

    public void sayHello() {
        System.out.println(" hello how are doing");
    }
}
