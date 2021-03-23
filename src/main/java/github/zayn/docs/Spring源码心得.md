# Spring源码心得

##容器的启动流程，beanfactory和applicationcontext的区别 
1. ContextLoaderListener 实现了 ServletContextListener 接口，故在web容器启动的时候，ContextLoaderListener 会监听到这个事件，contextInitialized(event) 方法被调用。(观察者模式，tomcat的启动监听器是ServletContextListener)。contextInitialized(event)方法调用的是initWebApplicationContext(ServletContextEvent)
2. initWebApplicationContext就是通过读spring-application.xml等配置初始化一个容器

##Spring 加载 class 文件
1. 主要调用方法是refresh() ，此方法在XmlWebApplicationContext 的三层父类 AbstractApplicationContext 
2. refresh()的重要方法:  
2.1 obtainFreshFactory()作用是初始化一个BeanFactory。主要是通过scan方法扫描包生成一个个BeanDefinition，spring内部会维护一个BeanDefinitionMap，把扫描的class文件维护在BeanDefinitionMap里。  
2.2 第二个重要方法是finishBeanFactoryInitialization(beanFactory)。主要是把BeanDefinition里的bean加载成单例对象。其中主要方法是doCreateBean(beanFactory)
doCreateBean完成了bean整个的生命周期流程，其中包括四个方法：
```
// 1. 实例化
BeanWrapper =createBeanInstance(beanName, mbd, args)
// 2. 属性赋值      
populateBean(beanName, mbd, instanceWrapper);      
// 3. 初始化       
exposedObject = initializeBean(beanName, exposedObject, mbd);
// 4. 销毁-注册回调接口  
registerDisposableBeanIfNecessary(beanName, bean, mbd);

2里主要完成了@autowire标记的bean的注入
3里主要完成了Aware，BeanPostProcessor 的前置处理 ，若实现 InitializingBean 接口，调用 afterPropertiesSet() 方法， init-method方法执行， BeanPostProceesor 后置处理等
```
