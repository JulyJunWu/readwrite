package com.ws.jvm.bytecode;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Jun
 * data  2019-08-15 23:29
 */
public class Test2 {
    String str = "Welcome";

    private int x = 5;
    private static int x2 = 5;

    public static Integer in = 10;

    public static void main(String[] args)throws Exception {

        ClassLoader classLoader = Test2.class.getClassLoader();

        Class<Unsafe> unsafeClass = Unsafe.class;

        Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");

        theUnsafe.setAccessible(true);

        Unsafe o = (Unsafe) theUnsafe.get(null);


    }


    public void setX(int x) {
        this.x = x;
    }

    private void test(){
        synchronized (this){
            System.out.println(1111);
        }
    }
}
