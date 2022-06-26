package com.xc.joy.offer.expand.pattern.singleton;

import java.io.*;

/**
 * @author lxcecho
 * @since 2021/1/4
 * <p>
 * 反序列化攻击
 */
public class DeserializeAttack {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StarvingSingleton singleton = StarvingSingleton.getInstance();
        byte[] bytes = serialize(singleton);
        Object deserializeInstance = deserialize(bytes);
        System.out.println((singleton == deserializeInstance));
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
