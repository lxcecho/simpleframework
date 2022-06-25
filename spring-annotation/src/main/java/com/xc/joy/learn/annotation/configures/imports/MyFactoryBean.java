package com.xc.joy.learn.annotation.configures.imports;

import com.xc.joy.learn.entity.Monkey;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyFactoryBean implements FactoryBean<Monkey> {
    @Nullable
    @Override
    public Monkey getObject() throws Exception {
        return new Monkey();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Monkey.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
