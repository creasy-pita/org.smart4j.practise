# org.smart4j.practise
HelperLoader
    加载不同的类定义到
    包括 
代码问题
    源码：Proxy proxy = ProxyManager.createProxy(targetClass, proxyList);
    问题：org.smart4j.chapter4.controller.CustomerController$$EnhancerByCGLIB$$75df5731 cannot be cast to org.smart4j.framework.proxy.Proxy
    分析：ProxyManager.createProxy 创建实际上是继承targetClass的代理，而不是切面代理
    解决：
    Object proxy = ProxyManager.createProxy(targetClass, proxyList);
    
    源码：request.getParameterNames() 不能获取 form 表单的内容
    问题：body 中 form 内容 需要通过  request.getInputStream() 获取
    
DatabaseHelper
    BasicDataSource:  
        from JAR : org.apache.commons » commons-dbcp2 
        description : Apache Commons DBCP software implements Database Connection Pooling
    QueryRunner  :
        from JAR : commons-dbutils » commons-dbutilsApache          
        description : The Apache Commons DbUtils package is a set of Java utility classes for easing JDBC development.
    ThreadLocal<Connection> 
        