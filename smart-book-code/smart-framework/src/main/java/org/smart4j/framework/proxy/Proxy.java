package org.smart4j.framework.proxy;

/**
 * Created by creasypita on 8/23/2019.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
