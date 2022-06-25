package com.xc.joy.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean 的使用
 *
 * @author lxcecho
 * @since 07.04.2021
 */
@Component
public class PersonFactory implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
