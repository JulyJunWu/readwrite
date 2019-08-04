package com.ws.future;

/**
 * @author Jun
 * data  2019-08-04 11:50
 */
public class FutureTask<T> implements Future<T> {

    private T result;

    private volatile boolean isDone;

    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {

        synchronized (LOCK) {

            while (!isDone) {
                LOCK.wait();
            }
            return result;
        }
    }

    protected void finish(T result) {

        synchronized (LOCK) {

            if (isDone)
                return;

            this.result = result;
            isDone = true;
            LOCK.notifyAll();
        }

    }

    @Override
    public boolean isDone() {
        return isDone;
    }

}
