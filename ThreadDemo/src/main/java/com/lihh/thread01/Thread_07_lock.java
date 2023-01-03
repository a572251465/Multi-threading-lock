package com.lihh.thread01;

import java.util.concurrent.locks.ReentrantLock;

public class Thread_07_lock {
    public static void main(String[] args) throws Exception {
        test01();
    }

    public static void test01() throws Exception {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                System.out.println("t1 end");
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            System.out.println("t2 end");
            try {
//                lock.lockInterruptibly();
                lock.lock();
            } finally {
                lock.unlock();
            }
        });

        t2.start();
        Thread.sleep(1000);

        t2.interrupt();
    }
}
