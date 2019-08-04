package com.ws.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author Jun
 * data  2019-08-04 0:27
 * <p>
 * <p>
 * 读写锁使用场景:
 * 当某个共享资源 读请求 远大于写请求时, 推荐使用 {@link ReentrantReadWriteLock}
 * <p>
 * 当写请求大于读请求时 -> 参考 {@link StampedLock};
 */
public class TestShareData {

    public static String text = "thisistestsharethread";

    public static void main(String[] args) {

        ShareData shareData = new ShareData(50);

        new Thread(() -> {
            IntStream.range(0, text.length()).forEach(i -> {
                try {
                    char c = text.charAt(i);
                    shareData.write(c);
                    System.out.println(currentThread() + " write -> " + c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).start();


        IntStream.range(0, 10).forEach(i -> {

            new Thread(() -> {
                try {
                    while (true) {
                        char[] read = shareData.read();
                        java.lang.String s = new java.lang.String(read);
                        System.out.println(new java.lang.String(read));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });

    }
}
