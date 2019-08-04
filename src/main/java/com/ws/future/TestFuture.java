package com.ws.future;

import java.util.concurrent.TimeUnit;

/**
 * @author Jun
 * data  2019-08-04 12:02
 */
public class TestFuture {

    public static void main(String[] args) throws Exception {

        //one();
        two();

    }

    public static void one() throws Exception {
        FutureService<Void, Void> futureService = FutureService.newService();

        Future<?> future = futureService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("complete");
        });


        Object result = future.get();

        System.out.println("返回结果 -> " + result);
    }


    public static void two() throws Exception {
        FutureService<String, Integer> futureService = FutureService.newService();

        Future<Integer> future = futureService.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return input.length();
        }, "ZWS", out -> {
            System.out.println("回调接口 : " + out);
        });


        Integer result = future.get();

        System.out.println("返回结果 -> " + result);
    }

}
