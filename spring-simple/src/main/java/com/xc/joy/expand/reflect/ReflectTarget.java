package com.xc.joy.expand.reflect;

/**
 * @author lxcecho
 * @since 2020/11/20
 */
public class ReflectTarget extends ReflectTargetOrigin {

    //---------------------构造函数--------------------------
    // 默认的带参构造函数
    ReflectTarget(String str) {
        System.out.println("默认的带参构造函数 " + str);
    }

    // 公有的无参构造函数
    public ReflectTarget() {
        System.out.println("调用了公有的无参构造方法...");
    }

    // 带有一个参数的公有构造函数
    public ReflectTarget(char name) {
        System.out.println("调用了带有一个参数的公有构造函数，参数值name=" + name);
    }

    // 带有多个参数的公有构造函数
    public ReflectTarget(String name, int index) {
        System.out.println("调用了带有多个参数的公有构造函数，参数值为：[name]=" + name + ",[index]=" + index);
    }

    // 受保护的构造函数
    protected ReflectTarget(boolean n) {
        System.out.println("调用了受保护的构造方法 n=" + n);
    }

    // 私有的构造函数
    private ReflectTarget(int index) {
        System.out.println("调用了私有的构造函数 index=" + index);
    }

    // ------------字段-------------
    char type;
    public String name;
    protected int index;
    private String targetInfo;

    @Override
    public String toString() {
        return "ReflectTarget{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", index=" + index +
                ", targetInfo='" + targetInfo + '\'' +
                '}';
    }

    // -------------成员方法---------------
    void show1() {
        System.out.println("调用了默认的，无参的show1");
    }

    public void show2(String s) {
        System.out.println("调用了公有的，String参数的show2");
    }

    protected void show3() {
        System.out.println("调用了受保护的，无参的show1");
    }

    private String show4(int index) {
        System.out.println("调用了私有的，并且有返回值的，int参数的show4(): index =" + index);
        return "show4 result";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取Class对象的三种方式

        // 1 Object——getClass();
        ReflectTarget reflectTarget = new ReflectTarget();
        Class reflectTargetClass1 = reflectTarget.getClass();
        System.out.println("lst：" + reflectTargetClass1.getName());

        //2 任何数据类型(包括基本数据类型)都有一个“静态”的class属性
        Class reflectTargetClass2 = ReflectTarget.class;
        System.out.println("2nd：" + reflectTargetClass2.getName());

        //判断第一种方式获取的class对象和第二种方式获取的是否是同一个
        System.out.println(reflectTargetClass1 == reflectTargetClass2);

        // 3 通过Class类的静态方法：forName(String className)(常用)
        Class reflectTargetClass3 = Class.forName("com.xc.joy.expand.reflect.ReflectTarget");
        System.out.println("3rd: " + reflectTargetClass3.getName());
        //判断第二种方式获取的class对象和第三种方式获取的是否是同一个
        System.out.println(reflectTargetClass2 == reflectTargetClass3);
    }

}
