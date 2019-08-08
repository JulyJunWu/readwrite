package com.ws.jvm.classloader;

/**
 * @author Jun
 * data  2019-08-08 22:49
 * <p>
 * 常量在编辑期间会存入调用方法所在的类的常量池中
 * 本质上,调用类并没有直接引用定义常量所在类,
 * 所以不会初始化常量所在的类
 */
public class TestOne {

    public static void main(String[] args) {
        //只输出 18
        System.out.println(TestTwo.num);
    }
}


class TestTwo {

    public static final int num = 18;

    static {
        System.out.println("TestTwo");
    }
}