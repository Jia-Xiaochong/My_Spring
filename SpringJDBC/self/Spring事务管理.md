#Spring 事务管理
##事务管理
一个数据库事务是一个被视为单一的工作单元的操作序列。这些操作应该要么完整地执行，要么完全不执行。事务管理是一个重要组成部分，RDBMS 面向企业应用程序，以确保数据完整性和一致性。事务的概念可以描述为具有以下四个关键属性说成是 ACID：

 * 原子性：事务应该当作一个单独单元的操作，这意味着整个序列操作要么是成功，要么是失败的。
 * 一致性：这表示数据库的引用完整性的一致性，表中唯一的主键等。
 * 隔离性：可能同时处理很多有相同的数据集的事务，每个事务应该与其他事务隔离，以防止数据损坏。
 * 持久性：一个事务一旦完成全部操作后，这个事务的结果必须是永久性的，不能因系统故障而从数据库中删除。
 
一个真正的 RDBMS 数据库系统将为每个事务保证所有的四个属性。使用 SQL 发布到数据库中的事务的简单视图如下：

 * 使用 begin transaction 命令开始事务。
 * 使用 SQL 查询语句执行各种删除、更新或插入操作。
 * 如果所有的操作都成功，则执行提交操作，否则回滚所有操作。

Spring 框架在不同的底层事务管理 APIs 的顶部提供了一个抽象层。Spring 的事务支持旨在通过添加事务能力到 POJOs 来提供给 EJB 事务一个选择方案。Spring 支持编程式和声明式事务管理。EJBs 需要一个应用程序服务器，但 Spring 事务管理可以在不需要应用程序服务器的情况下实现。

##局部事物 vs. 全局事务
局部事务是特定于一个单一的事务资源，如一个 JDBC 连接，而全局事务可以跨多个事务资源事务，如在一个分布式系统中的事务。

局部事务管理在一个集中的计算环境中是有用的，该计算环境中应用程序组件和资源位于一个单位点，而事务管理只涉及到一个运行在一个单一机器中的本地数据管理器。局部事务更容易实现。

全局事务管理需要在分布式计算环境中，所有的资源都分布在多个系统中。在这种情况下事务管理需要同时在局部和全局范围内进行。分布式或全局事务跨多个系统执行，它的执行需要全局事务管理系统和所有相关系统的局部数据管理人员之间的协调。

##编程式 vs. 声明式
Spring 支持两种类型的事务管理:

 * 编程式事务管理 ：这意味着你在编程的帮助下有管理事务。这给了你极大的灵活性，但却很难维护。
 * 声明式事务管理 ：这意味着你从业务代码中分离事务管理。你仅仅使用注释或 XML 配置来管理事务。

声明式事务管理比编程式事务管理更可取，尽管它不如编程式事务管理灵活，但它允许你通过代码控制事务。但作为一种横切关注点，声明式事务管理可以使用 AOP 方法进行模块化。Spring 支持使用 Spring AOP 框架的声明式事务管理。

<hr />

Spring 的事务管理是基于 AOP 实现的，而 AOP 是以方法为单位的。Spring 的事务属性分别为传播行为、隔离级别、只读和超时属性，这些属性提供了事务应用的方法和描述策略。

在 Java EE 开发经常采用的分层模式中，Spring 的事务处理位于业务逻辑层，它提供了针对事务的解决方案。

在 Spring 解压包的 libs 目录中，包含一个名称为 spring-tx-x.x.x.RELEASE.jar 的文件，该文件是 Spring 提供的用于事务管理的 JAR 包，其中包括事务管理的三个核心接口：PlatformTransactionManager、TransactionDefinition 和 TransactionStatus。

这三个核心接口的作用及其提供的方法如下:

## ① PlatformTransactionManager 事务管理器

    public interface PlatformTransactionManager {
        // 获取事务
        TransactionStatus getTransaction(TransactionDefinition definition);
        // 提交事务
        void commit(TransactionStatus status) throws TransactionException;
        // 回滚事务
        void rollback(TransactionStatus status) throws TransactionException;
    }
    
在项目中，Spring 将 xml 中配置的事务详细信息封装到对象 TransactionDefinition 中，然后通过事务管理器的 getTransaction() 方法获得事务的状态。
    
## ② TransactionDefinition 事务的一些基础信息，如超时时间、隔离级别、传播属性等

    public interface TransactionDefinition {
       // 获取事务的传播行为，Spring 提供了与 EJB CMT 类似的所有的事务传播选项。
       int getPropagationBehavior();
       // 获取事务的隔离级别
       int getIsolationLevel();
       // 获取事务对象名称
       String getName();
       // 获取事务的超时时间
       int getTimeout();
       // 获取事务是否只读
       boolean isReadOnly();
    }
    
###下面是隔离级别的可能值:  

| 隔离级别 | 描述
| ---- | ----
| TransactionDefinition.ISOLATION_DEFAULT | 这是默认的隔离级别。
| TransactionDefinition.ISOLATION_READ_COMMITTED | 表明能够阻止误读；可以发生不可重复读和虚读。
| TransactionDefinition.ISOLATION_READ_UNCOMMITTED | 表明可以发生误读、不可重复读和虚读。
| TransactionDefinition.ISOLATION_REPEATABLE_READ | 表明能够阻止误读和不可重复读；可以发生虚读。
| TransactionDefinition.ISOLATION_SERIALIZABLE | 表明能够阻止误读、不可重复读和虚读。

###下面是传播类型的可能值:

| 传播类型 | 描述
| ---- | ----
| TransactionDefinition.PROPAGATION_MANDATORY | 支持当前事务；如果不存在当前事务，则抛出一个异常。
| TransactionDefinition.PROPAGATION_NESTED | 如果存在当前事务，则在一个嵌套的事务中执行。
| TransactionDefinition.PROPAGATION_NEVER | 不支持当前事务；如果存在当前事务，则抛出一个异常。
| TransactionDefinition.PROPAGATION_NOT_SUPPORTED | 不支持当前事务；而总是执行非事务性。
| TransactionDefinition.PROPAGATION_REQUIRED | 支持当前事务；如果不存在事务，则创建一个新的事务。
| TransactionDefinition.PROPAGATION_REQUIRES_NEW | 创建一个新事务，如果存在一个事务，则把当前事务挂起。
| TransactionDefinition.PROPAGATION_SUPPORTS | 支持当前事务；如果不存在，则执行非事务性。
| TransactionDefinition.TIMEOUT_DEFAULT | 使用默认超时的底层事务系统，或者如果不支持超时则没有。

## ③ TransactionStatus 事务的一些状态信息，如是否一个新的事务、是否已被标记为回滚

    public interface TransactionStatus extends SavepointManager {
       // 是否是新的事务
       boolean isNewTransaction();
       // 是否存在保存点
       boolean hasSavepoint();
       // 设置事务回滚
       void setRollbackOnly();
       // 事务是否回滚
       boolean isRollbackOnly();
       // 事务是否完成
       boolean isCompleted();
    }

> Spring 的事务管理有两种方式：一种是传统的编程式事务管理，即通过编写代码实现的事务管理；另一种是基于 AOP 技术实现的声明式事务管理。

#Spring 编程式事务管理

编程式事务管理方法允许你在对你的源代码编程的帮助下管理事务。这给了你极大地灵活性，但是它很难维护。

Spring提供两种方式的编程式事务管理，分别是：使用TransactionTemplate和直接使用PlatformTransactionManager。

##Spring 声明式事务管理
Spring 声明式事务管理在底层采用了 AOP 技术，其最大的优点在于无须通过编程的方式管理事务，只需要在配置文件中进行相关的规则声明，就可以将事务规则应用到业务逻辑中。

Spring 实现声明式事务管理主要有两种方式：
 * 基于 XML 方式的声明式事务管理。
 * 通过 Annotation 注解方式的事务管理。

在事务的帮助下，我们可以实现各种 CRUD 操作,下面是与声明式事务相关的步骤：
  
 * 我们使用标签，它创建一个事务处理的建议，同时，我们定义一个匹配所有方法的切入点，我们希望这些方法是事务型的并且会引用事务型的建议。
 * 如果在事务型配置中包含了一个方法的名称，那么创建的建议在调用方法之前就会在事务中开始进行。
 * 目标方法会在 try / catch 块中执行。
 * 如果方法正常结束，AOP 建议会成功的提交事务，否则它执行回滚操作。
 
下面通过银行转账的案例讲解如何使用 XML 的方式实现 Spring 的声明式事务处理。首先建表：
 
     USE spring;
     CREATE TABLE account (
         id INT (11) PRIMARY KEY AUTO_INCREMENT,
         username VARCHAR(20) NOT NULL,
         money INT DEFAULT NULL
     );
     INSERT INTO account VALUES (1,'zhangsan',1000);
     INSERT INTO account VALUES (2,'lisi',1000);
