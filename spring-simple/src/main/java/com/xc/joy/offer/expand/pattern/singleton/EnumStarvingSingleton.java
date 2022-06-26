package com.xc.joy.offer.expand.pattern.singleton;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class EnumStarvingSingleton implements Serializable {

    private EnumStarvingSingleton() {
    }

    public static EnumStarvingSingleton getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private EnumStarvingSingleton instance;

        ContainerHolder() {
            instance = new EnumStarvingSingleton();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        /**
         * 反射攻击
         */
        EnumStarvingSingleton instance = EnumStarvingSingleton.getInstance();
        /*System.out.println(instance);
        Class clazz = EnumStarvingSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumStarvingSingleton newInstance = (EnumStarvingSingleton) constructor.newInstance();
        System.out.println(newInstance.getInstance());
        System.out.println(instance == newInstance.getInstance());*/

        /**
         * 反序列化攻击
         */
        byte[] bytes = serialize(instance);
        EnumStarvingSingleton deserialize = (EnumStarvingSingleton)deserialize(bytes);
        System.out.println(instance == deserialize.getInstance());

    }

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

}
