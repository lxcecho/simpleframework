package com.xc.joy.learn.annotation.configures.imports;

import com.xc.joy.learn.entity.Cat;
import com.xc.joy.learn.entity.Company;
import com.xc.joy.learn.entity.Member;
import com.xc.joy.learn.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
@Configuration
@Import(value = {Cat.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MyConfig {

    @Bean
    public Company company() {
        return new Company();
    }

    @Bean
    public Member member() {
        return new Member();
    }

    //给IoC中注册Bean的方式
    //1、@Bean 直接导入单个类
    //2、@ComponentScan 包扫描默认是扫描（@Controller、@Service、@Repository、@Component）
    //3、@Import 快速给容器导入组件Bean
    //      a. @Import 直接传参导入
    //      b. ImportSelector 自定义导入规则
    //      c.ImportBeanDefinitionRegistrar ,使用BeanDefinitionRegistry可以手动注入到IoC容器中
    //4、FactoryBean 把需要注入的对象封装为FactoryBean
    //      a、FactoryBean 负责将Bean注入到容器的Bean
    //      b、BeanFactory 从IoC中获得Bean对象
    @Bean
    public Person person() {
        return new Person("Tom", 18);
    }

    @Bean
    public MyFactoryBean monkey() {
        return new MyFactoryBean();
    }
}
