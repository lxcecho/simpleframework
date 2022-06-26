1 Spring 中的 Bean 是线程安全的么？为什么？

> Spring 中的 Bean 是否线程安全和 Spring 无关。因为 Spring 只承担了创建和管理 Bean 的职责，并没有对 Bean 进行任何修改。

2 Spring IOC 和 DI 的工作原理？

> Spring IOC 基本流程:
> 
>> 1、读取配置文件；
>> 
>> 2、解析配置文件，并封装成 BeanDefinition；
>>
>> 3、把 BeanDefinition 对应的实例放入到容器进行缓存；
> 
> DI 基本流程
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

