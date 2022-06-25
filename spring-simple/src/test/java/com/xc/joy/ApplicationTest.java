package com.xc.joy;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public class ApplicationTest {
    @Test
    public void testSet(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(1);

        set.forEach(integer -> {
            System.out.println(integer);
        });

    }
}
