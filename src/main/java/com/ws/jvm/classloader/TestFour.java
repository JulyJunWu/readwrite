package com.ws.jvm.classloader;

import java.util.UUID;

/**
 * @author Jun
 * data  2019-08-09 21:11
 *
 * 当调用接口中编译期间能确定的常量, 那么是不会触发父接口初始化
 *
 * 当调用接口中编辑期间不能确定的常量,则会对所有父接口进行初始化
 *
 */
public class TestFour {
    public static void main(String[] args) {
        System.out.println(FourSon.STR);
    }
}

/**
 * 接口中不能使用代码块
 */
interface FourParent {
    //接口中的变量默认都是(静态常量) public static final修饰的
    public static final int A = 10;
    String STR2 = UUID.randomUUID().toString();
}

interface FourSon extends FourParent {
    public static final int B = 11;
    String STR = UUID.randomUUID().toString();
}


