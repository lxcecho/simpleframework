1 Spring 中的 Bean 是线程安全的么？为什么？

> Spring 中的 Bean 是否线程安全和 Spring 无关。因为 Spring 只承担了创建和管理 Bean 的职责，并没有对 Bean 进行任何修改。

2 Spring IOC 和 DI 的工作原理？

> ### Spring IOC 基本流程:
> 
> 定位【定位配置文件和扫面相关的注解】--->加载【将配置信息载入到内存中】--->注册【根据载入的信息，将对象初始化到 IOC 容器中】
> 
> **基于 XML 的定位、加载和注册：**
> 
> 寻找入口--获取配置文件路径--开始启动--创建容器--载入配置路径--分片路径处理策略--解析配置文件路径-开始读取配置文件--准备文档对象--分配解析策略--将配置载入内存--载入 bean 标签--载入 property 标签--载入 property 子标签--载入 list 标签--**分配注册策略**--**向容器注册**
>
> **基于 注解 的容器初始化过程：**
> 
> 定位 Bean 扫描路径--读取元素据--解析--注册 Bean
> 
>> 1、读取配置文件；
>> 
>> 2、解析配置文件，并封装成 BeanDefinition；
>>
>> 3、把 BeanDefinition 对应的实例放入到容器进行缓存；
> 
> ### DI 基本流程
> 
> 寻找入口-->开始实例化-->选择实例化策略-->执行实例化-->准备依赖注入-->解析注入规则-->注入赋值
> 
>> 1、循环读取 BeanDefinition 的缓存信息；
>> 
>> 2、调用 getBean() 方法创建对象的实例；
>> 
>> 3、将创建好的对象实例包装为 BeanWrapper 对象；
>> 
>> 4、将 BeanWrapper 对象缓存到 IOC 容器；
>> 
>> 5、循环 IOC 容器执行依赖注入。

3 手绘 Spring IOC 和 DI 部分类结构图

BeanDefinition：保存每个 Bean 配置的解析结果；

BeanWrapper：保存原始对象啊和原始对象的 class；

ApplicationContext：Spring 中最核心的 IOC 工厂，也是 Spring 的主入口；

其中 BeanDefinition、BeanWrapper 和 ApplicationContext 三者关系如下图：

![img_1.png](img_1.png)

依赖注入关键类：

**IOC 容器：**

ApplicationContext

BeanDefinition

BeanFactory  getBean() 采用的单例 Bean，容器式（lazy）

AbstractBeanFactory

实例化策略：

SimpleInstantiateStrategy

存储实例所有相关信息 scope、proxy、instance

BeanWrapper

统一一个对外访问对象的入口，扩展一些功能，缓存一些配置信息

**DI 依赖注入从哪里开始？**

getBean() why

因为 Spring 默认是 **懒加载** 

getBean（）--instantiateBean() 反射初始化 Bean 独享，发起实例化对象的动作

populateBean() 完成依赖注入，用反射注入，发起依赖注入的动作

> 根据 beanName、beanDefinition、beanWrapper 找到需要赋值的属性，把需要赋值的属性封装成一个集合 PropertyValues，集合的元素 PropertyValue，需要赋值的 Bean，赋值需要调用的方法，要赋什么值？

实例化有两种情况：

- 目标类配置了 AOP，实例话的对象为代理类

- 目标类没有配置 AOP，实例化原生对象

factoryBeanInstanceCache 用来存 BeanWrapper 的 Map，存储原生 Bean 的包装类

factoryBeanObjectCache 用来存原生 Bean 的 Map，存储反射创建出来的实际的对象

beanDefinitionMap 用来存 BeanDefinitionMap，存储配置信息

**循环依赖注入，用缓存解决依赖注入的问题**

> FactoryBean：Spring 内部实现的一种规范 & 开头作为 BeanName
>
> Spring 中的所有容器都是 FactoryBean
> 
> 因为容器本身也由容器管理，Root 来创建，都是单例放在 IOC 容器中。
> 
> BeanFactory：Bean 工厂的顶层规范，只是定义了 getBean 方法

### Spring AOP

切面 Aspect：面向规则，具有相同规则的方法的集合体；

通知 Advice：回调；

切入点 Pointcut：需要代理的具体方法；

目标对象 Target Object：被代理的对象；

AOP 代理 AOP Proxy：主要两种方式：JDK、Cglib；

前置通知 Before Advice：在 invoke Pointcut 之前调用，织入的方法；

后置通知 After Advice：Pointcut 之后调用，织入的方法；

返回后通知 After Return Advice：返回值为 void，织入的方法；

环绕通知 Around Advice：只要触发调用，织入的方法；

异常通知 After Throwing Advice：Pointcut 抛出异常，，织入的方法；

**寻找入口-->选择策略-->调用方法-->触发通知**

### Spring MVC

九大组件：

- HandleMappings

- HandleAdapters

- HandleExceptionResolvers

- ViewResolvers

- RequestToViewNameTranslator

- LocalResolver

- ThemeResolver

- MultipartResolver

- FlashMapManager

Spring MVC 使用优化建议：

- Controller 如果能保持单例，尽量使用单例

> 这样可以减少创建对象和回收对象的开销。也就是说，如果 Controller 的类变量和实例变量可以以方法形参声明的尽量以方法的形参生命，不要以类变量和实例变量声明，这样可以避免线程安全问题。

- 处理 Request 的方法中的形参务必添加上 @RequestParam 注解

> 这样可以避免 Spring MVC 使用 asm 框架读取 class 文件获取方法参数名的过程。即便 Spring MVC 对读取出的方法参数名进行了缓存，如果不要读取 class 文件当然是更好。

- 缓存 URL

> 阅读源码过程中，我们发现 Spring MVC 并没有对处理 URL 的方法进行缓存，也就是说每次都要根据请求 URL 去匹配 Controller 中的方法 URL，如果把 URL 和 Method 的关系缓存起来，会不会带来性能上的提升呢？有点恶心的是，负责解析 URL 和 Method 对应关系的 ServletHandlerMethodResolver 是一个 private 的内部类【这个好像在后边更新的版本上做了修改】，不能直接继承该类增强代码，必须要改代码后重新编译。当然，缓存起来，必须要考虑缓存的线程安全问题。
 
### Spring 事务管理

事务（Transaction）是访问并可能更新数据库各种数据项的一个程序执行单元（unit）。

特点：事务是恢复和并发控制的基本单位。事务具有四个属性：原子性、一致性、隔离性和持久性，即 ACID。

- 原子性 Atomicity：一个事务是一个不可分割的工作单位，食物中包括的诸操作要么做，要么都不做；

- 一致性 Consistency：事务必须是使数据库从一个一致性状态变到另一个一致性状态，一致性与原子性使密切相关的；

- 隔离性 Isolation：一个事务的执行不能被其他事务干扰，即 一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰；

- 持久性 Durability：也称永久性（Permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的，接下来的其他操作或故障不应该对其有任何影响。

**Spring 事务传播属性：**

1. PROPAGATION_REQUIRED 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择，也是 Spring 默认的事务传播。

2. PROPAGATION_REQUIRES_NEW 新建事务，如果当前存在事务，把当前事务挂起。新建的事务将和挂起的事务没有任何关系，是两个独立的事务，外层事务失败回滚之后，不能回滚内层事务执行的结果，内层事务失败抛出异常，外层事务捕获，也可以不处理回滚操作。

3. PROPAGATION_SUPPORTS 支持当前事务，如果当前没有事务，就以非事务方式运行。

4. PROPAGATION_MANDATORY 支持当前事务，如果当前没有事务，就抛出异常。

5. PROPAGATION_NOT_SUPPORT 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。

6. PROPAGATION_NEVER 以非事务方式执行，如果当前存在事务，则抛出异常。

7. PROPAGATION_NESTED 如果一个活动的事务存在，则运行在一个嵌套的事务中；如果没有活动事务，则按 REQUIRED 属性执行。他使用了一个单独的事务，这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。他支队 DataSourceTransactionManager 事务管理器起效。

**数据库事务的隔离级别：**

1. Read_Uncommitted：隔离级别的值 0，导致脏读；

2. Read_Committed：隔离级别的值 1，避免脏读，允许不可重复读和幻读；

3. Repeatable_Read：隔离级别的值 2，避免脏读，不可重复读，允许幻读；

4. Serializable：隔离级别的值 3，串行化读 ，事务只能一个一个执行，避免了脏读、不可重复读、幻读。执行效率慢，使用时慎重。

> - **脏读**：一事务对数据进行了增删改，但未提交，另一事务可以读取到未提交的数据。如果第一个事务这时候回滚了，那么第二个事务就读到了脏数据。
> 
> - **不可重复读**：一个事务中发生了两次读操作，第一次读操作和第二次操作之间，另外一个事务对数据进行了修改，这时候读取到的数据是不一致的。
> 
> - **幻读**：第一个事务对一定范围的数据进行批量修改，第二个事务在这个范围增加一条数据，这时候第一个事务就会丢失对新增数据的修改。
> 
> 总结：隔离级别越高，越能保证数据的完整性和一致性，但是对并发性能的影响也越大。大多数的数据库默认隔离级别为 Read_Committed，比如 SqlServer、Oracle；少数数据库默认隔离级别为：Repeatable_Read，比如：Mysql，InnoDB。

**Spring 事务的隔离级别：**

1. ISOLATION_DEFAULT：这个是 PlatformTransactionManager 默认的隔离级别，使用数据库默认的事务隔离级别，另外四个与 JDBC 的隔离级别相对应；

2. ISOLATION_READ_UNCOMMITTED 这是事务最低的隔离级别，他允许另一个事务可以看到这个事务未提交的数据，这个隔离级别会产生脏读，不可重复读和幻读；

3. ISOLATION_READ_COMMITTED 保证一个事务修改的数据提交后才能被另一个事务读取，另一个事务不能读取该事务未提交的数据；

4. ISOLATION_REPEATABLE_READ 这种事务隔离级别可以防止脏读，不可重复读，但是可能会出现幻读；

5. ISOLATION_SERIALIZABLE 这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。

**真正的数据库层的食物提交和回滚是通过 binlog 或者 redo log 实现的。**

# 常见面试题

1. 使用 Spring 框架能给我们带来哪些好处？

简化开发

提供内置的解决方案BOP（面向Bean 编程）、IOC（控制反转）、AOP（面向切面编程）

声明式事务管理，TransactionManager

提供诸多的工具类，围绕 Spring 生态，比如 JdbcTemplate。

2. BeanFactory 和 ApplicationContext 有啥区别？

ApplicationContext 是 BeanFactory 的实现类

BeanFactory 是顶层设计（抽象），而 ApplicationContext 是 User Interface

功能会非常高丰富，API 是最全的，一般会认为 ApplicationContext 就是 IOC

IOC 的功能是在 DefaultListableBeanFactory 类中完成的，但是有共同的接口

3. Spring Bean 的声明周期

即从创建、到调用、到销毁

singleton 从 Spring 容器的启动到 Spring 容器的销毁，如果是延时加载，在调用前创建对象

prototype 在调用前创建，调用后销毁，作用域决定了生命周期的长短

4. Spring Bean 各作用域之间区别？

- singleton 作用域全局，在任何地方可以通过 IOC 获取

- prototype 全局

- request 在一次请求发起和结束之间

- session 一个 session 创建和 session 失效之间，一般默认是 30 分钟

- global-session porlet 可以理解为容器中的一个应用【Spring5 不再支持】

6. Spring 中 Bean 是线程安全的么？

坑！！！！！！！！问的就是自己写的 bean 是线程安全的吗？

Spring 中的 Bean 是否线程安全和 Spring 无关，和自己写的代码有关。

7. Spring 中用到了哪些设计模式？？？

工厂模式、单例模式（容器式单例）、原型（多例）模式、代理模式、享元模式、门面模式、适配器模式、委派模式、装饰器模式、责任链模式、空对象模式、解释器模式

8. Spring、SpringBoot、SpringCloud 到底有什么区别？？

Spring 已有的生态，他完成日常开发的所有功能，一个 Spring 打天下。SpringBoot，简化开发，Spring 简(减)的还不够，因为配置文件很头疼，配置的管理（架构师很累）内置了默认的配置，我们需要配的是需要覆盖的，不需要覆盖实现零配置，全面的去 Servlet 化，能够自运行了，部署也简单了，一个 jar 包打天下，不需要 Tomcat、Jetty。官方层面提供了一套脚手架，一键搭建，节省时间。

SpringCloud 正式进军分布式，注册中心、服务发现、监控【链路追踪、配置中心、负载、熔断...打造一个生态，一站式分布式解决方案。Netflix，国外类似爱奇艺的视频网站，基因。

Dubbo RPC，分布式服务治理，Netty、Spring、Zookeeper、Nginx...

SpringCloud 依赖 SpringBoot，SpringBoot 依赖 Spring...

9. Spring 的事务是如何传播的？？

10. Spring 如何接管 MyBatis 的事务？？
 
想办法拿到 Connection

11. BeanFactory 和 FactoryBean 的区别？

BeanFactory 是 IOC 容器的顶层设计，FactoryBean 用来构建 Bean 的一个包装类。

12. 项目中如何应用 AOP？

声明式事务管理、日志监听、权限管理、安全监控...

13. Spring XML 和注解方式混用？

统一一种方式，要么 XMl，要么 Annotation。


14. 一个方法内，for 循环内重复操作数据库，是用一个连接还是多个连接？？？？

**千万不要在一个方法内 for 循环去查询数据库**，因为多次连接数据库，导致连接池的数量不可控，循环的次数未知，如果说你能确定一定是循环多少次，比如 100 次。

解决方案：

- 分页、缓存、联合查询...
































































