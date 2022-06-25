package com.xc.joy.expand.pattern.singleton;

import java.io.*;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public class DefendDeserializeAttackSingleton implements Serializable {

    private static DefendDeserializeAttackSingleton instance;

    private DefendDeserializeAttackSingleton() {
    }

    public static DefendDeserializeAttackSingleton getInstance() {
        if (instance == null) {
            instance = new DefendDeserializeAttackSingleton();
        }
        return instance;
    }

    /**
     * 增加一个 readResolve 方法并返回 instance 对象。当 ObjectInputStream 类反序列化时，
     * 如果对象存在 readResolve 方法，则会调用该方法返回对象。
     *
     * @return
     */
    private Object readResolve() {
        return instance;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 我们通过分析源码以及调试，我们可以看到实际上实例化了两次，只不过新创建的对象没有被返回而已
        DefendDeserializeAttackSingleton instance = DefendDeserializeAttackSingleton.getInstance();
        byte[] bytes = serialize(instance);
        Object deserialize = deserialize(bytes);
        System.out.println(instance == deserialize);
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
