#Spring AOP
面向切面编程（AOP）和面向对象编程（OOP）类似，也是一种编程模式。Spring AOP 是基于 AOP 编程模式的一个框架，它的使用有效减少了系统间的重复代码，达到了模块间的松耦合目的。

AOP 的全称是“Aspect Oriented Programming”，即面向切面编程，它将业务逻辑的各个部分进行隔离，使开发人员在编写业务逻辑时可以专心于核心业务，从而提高了开发效率。

AOP 采取横向抽取机制，取代了传统纵向继承体系的重复性代码，其应用主要体现在事务处理、日志管理、权限控制、异常处理等方面。

目前最流行的 AOP 框架有两个，分别为 Spring AOP 和 AspectJ。

Spring AOP 使用纯 Java 实现，不需要专门的编译过程和类加载器，在运行期间通过代理方式向目标类植入增强的代码。

AspectJ 是一个基于 Java 语言的 AOP 框架，从 Spring 2.0 开始，Spring AOP 引入了对 AspectJ 的支持。AspectJ 扩展了 Java 语言，提供了一个专门的编译器，在编译时提供横向代码的植入。

为了更好地理解 AOP，就需要对 AOP 的相关术语有一些了解，这些专业术语主要包含 Joinpoint、Pointcut、Advice、Target、Weaving、Proxy 和 Aspect，它们的含义如下表所示。

| 名称 | 说明
| ---- | ----
| Joinpoint（连接点）|指那些被拦截到的点，在 Spring 中，可以被动态代理拦截目标类的方法。
| Pointcut（切入点） |指要对哪些 Joinpoint 进行拦截，即被拦截的连接点。
| Advice（通知）    |指拦截到 Joinpoint 之后要做的事情，即对切入点增强的内容。
| Target（目标）		|指代理的目标对象。
| Weaving（植入）	|指把增强代码应用到目标上，生成代理对象的过程。
| Proxy（代理）		|指生成的代理对象。
| Aspect（切面）		|切入点和通知的结合。

通知（Advice）其实就是对目标切入点进行增强的内容，Spring AOP 为通知（Advice）提供了 org.aopalliance.aop.Advice 接口。

Spring 通知按照在目标类方法的连接点位置，可以分为以下五种类型，如表 1 所示。

| 接口 | 名称 | 说明
| ---- | ---- | ----
| org.springframework.aop.MethodBeforeAdvice      | 前置通知 | 	在方法之前自动执行的通知称为前置通知，可以应用于权限管理等功能。
| org.springframework.aop.AfterReturningAdvice    | 后置通知 | 	在方法之后自动执行的通知称为后置通知，可以应用于关闭流、上传文件、删除临时文件等功能。
| org.aopalliance.intercept.MethodInterceptor     | 环绕通知 | 	在方法前后自动执行的通知称为环绕通知，可以应用于日志、事务管理等功能。
| org.springframework.aop.ThrowsAdvice            | 异常通知 | 	在方法抛出异常时自动执行的通知称为异常通知，可以应用于处理异常记录日志等功能。
| org.springframework.aop.IntroductionInterceptor | 引介通知 | 	在目标类中添加一些新的方法和属性，可以应用于修改旧版本程序（增强类）。

#声明式 Spring AOP
Spring 创建一个 AOP 代理的基本方法是使用 org.springframework.aop.framework.ProxyFactoryBean，这个类对应的切入点和通知提供了完整的控制能力，并可以生成指定的内容。

ProxyFactoryBean 类中的常用可配置属性如下所示：

##ProxyFactoryBean 的常用属性
| 属性名称           | 描  述
| ---- | ----
| target            | 代理的目标对象
| proxyInterfaces   | 代理要实现的接口，如果有多个接口，则可以使用以下格式赋值：\<list>\<value>\</value>...\</list>
| proxyTargetClass  | 是否对类代理而不是接口，设置为 true 时，使用 CGLIB 代理
| interceptorNames  | 需要植入目标的 Advice
| singleton         | 返回的代理是否为单例，默认为 true（返回单实例）
| optimize          | 当设置为 true 时，强制使用 CGLIB

#使用AspectJ开发AOP：基于XML和基于Annotation
相关Java知识（JDK动态代理、CGLlB动态代理）
##基于Annotation
在 Spring 中，尽管使用 XML 配置文件可以实现 AOP 开发，但是如果所有的相关的配置都集中在配置文件中，势必会导致 XML 配置文件过于臃肿，从而给维护和升级带来一定的困难。

为此，AspectJ 框架为 AOP 开发提供了另一种开发方式——基于 Annotation 的声明式。AspectJ 允许使用注解定义切面、切入点和增强处理，而 Spring 框架则可以识别并根据这些注解生成 AOP 代理。

关于 Annotation 注解的介绍如下表所示：

| 名称 | 说明
| ---- | ----
| @Aspect | 用于定义一个切面。
| @Before | 用于定义前置通知，相当于 BeforeAdvice。
| @AfterReturning | 用于定义后置通知，相当于 AfterReturningAdvice。
| @Around | 用于定义环绕通知，相当于MethodInterceptor。
| @AfterThrowing | 用于定义抛出通知，相当于ThrowAdvice。
| @After | 用于定义最终final通知，不管是否异常，该通知都会执行。
| @DeclareParents | 用于定义引介通知，相当于IntroductionInterceptor（不要求掌握）