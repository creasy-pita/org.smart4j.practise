# org.smart4j.practise

	
###  简单切面编程的原理

1 解析目标类集合， 通过classloader 得到固定packagename下的类作为目标类集合
	主要包括 controller,service
2 解析代理类 包括aspect注解，transaction注解 （说明aspect注解的类会有与目标类的关系）
3 产生代理类proxy，主要作用时记录由目标类和aspect代理组成的代理链
4 使用beanmap<class,proxy> 记录[目标类名.class]和代理关系

> 2，3，5 部分查看https://github.com/creasy-pita/org.smart4j.practise/blob/master/smart-book-code/smart-framework/src/main/java/org/smart4j/framework/helper/AopHelper.java中的static部分

5 DispatcherServlet 实例对象初始化的时候通过以上几步得到 beanmap
6 DispatcherServlet处理httprequest时，先通过路径和controller的映射关系找到目标class,并以[目标类名.class]为key 在beanmap中找到代理链去执行，返回执行结果

### 运行方式

- 创建java web项目
- idea IDE 添加tomcat server configuration 参考[run-debug-configuration-tomcat-server](https://www.jetbrains.com/help/idea/run-debug-configuration-tomcat-server.html)
- 在 tomcat server 加入要部署的项目的war包，或者war exploded 包
- 启动，使用http://localhost:8080/chapter1_war_exploded/hello 访问

参考 架构探险从零开始写JavaWeb框架.pdf --chapter1

### 类的解读

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
    
AOP框架
    本项目切面类主要包括  继承 aspectProxy  TransactionProxy 
    aspectProxy 
        定义 @aspect 注解
        搭建 aspectProxy 代理框架
        加载 aspectProxy 部分的AOP框架
    TransactionProxy 
        定义 @transaction 注解
        搭建 TransactionProxy 代理框架
        加载 TransactionProxy 部分的AOP框架        