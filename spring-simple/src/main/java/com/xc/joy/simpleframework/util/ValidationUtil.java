package com.xc.joy.simpleframework.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public class ValidationUtil {

    /**
     * String是否为null 或者 ""
     *
     * @param obj
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
     * Array 是否为 null 或者 size 为0
     *
     * @param objects
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return (objects == null || objects.length == 0);
    }

    /**
     * Collection是否为null 或者 size为0
     *
     * @param collection 集合类型
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Map是否为null 或者 size为0
     *
     * @param map Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }
}
