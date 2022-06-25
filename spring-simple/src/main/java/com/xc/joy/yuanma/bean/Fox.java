package com.xc.joy.yuanma.bean;

import com.xc.joy.yuanma.ioc.anno.Autowired;
import com.xc.joy.yuanma.ioc.anno.Component;

/**
 * @author lxcecho 909231497@qq.com
 */
@Component
public class Fox {

    @Autowired
    private Cat cat;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Fox{" +
                "cat=" + cat +
                '}';
    }
}
