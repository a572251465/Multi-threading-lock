package com.lihh.thread01;

import java.util.concurrent.CountDownLatch;

public class Thread_11_i_add {

    private static int count = 0;
    private static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        test01();
    }

    public static void test01() throws InterruptedException {
        int MAX_COUNT = 100;
        Thread[] threads = new Thread[MAX_COUNT];
        CountDownLatch countDownLatch = new CountDownLatch(MAX_COUNT);

        for (int i = 0; i < MAX_COUNT; i++) {
            threads[i] = new Thread(() -> {
//                synchronized (o) {
                    for (int j = 0; j < 100; j++) {
                        count ++;
                    }
                    countDownLatch.countDown();
//                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        countDownLatch.await();
        System.out.println(count);
    }
}
