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