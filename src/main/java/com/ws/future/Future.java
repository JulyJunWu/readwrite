package com.ws.future;

/**
 * @author :Jun
 * date : 2019-08-04 11:40
 */
public interface Future<T> {

    //获取返回值
    T get() throws InterruptedException;

    //是否执行成功
    boolean isDone();

}
