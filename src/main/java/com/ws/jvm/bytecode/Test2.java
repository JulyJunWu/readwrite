package com.ws.jvm.bytecode;

/**
 * @author Jun
 * data  2019-08-15 23:29
 */
public class Test2 {
    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.setX(8);

        in = 20;

    }


    public void setX(int x) {
        this.x = x;
    }
}
