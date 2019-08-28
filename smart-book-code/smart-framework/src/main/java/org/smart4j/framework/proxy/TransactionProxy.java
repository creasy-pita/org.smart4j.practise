package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * Created by creasypita on 8/28/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class TransactionProxy implements Proxy {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    private final static ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Method  method = proxyChain.getTargetMethod();
        boolean flag = FLAG_HOLDER.get();
        if(!flag && method.isAnnotationPresent(Transaction.class)) {
            try {
                FLAG_HOLDER.set(true);
                DatabaseHelper.beginTransaction();
                LOGGER.info("begin transaction ");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                LOGGER.info("commit transaction ");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                LOGGER.info("rollback transaction ");
            } finally {
                FLAG_HOLDER.remove();
            }
        }
        else{
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
