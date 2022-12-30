package com.lihh;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class Thread_01_computed {
    public static Long MAX_COUNT = 1000000000L;

    @Test
    public void test01() {
        System.out.println("通过单线程的方式来实现: ");
        Long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i += 1) {}
        Long end = System.currentTimeMillis();

        System.out.println("花费时间为：" + (end - start));
    }

    public void commonHandle(int count) throws Exception {
        // 将内容分为多分
        int diff = (int) (MAX_COUNT / count);
        // 存放new 出来的线程
        Thread[] threads = new Thread[count];
        // 并发工具CountDownLatch 实现线程等待的工具
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            int finalI = i;

            threads[i] = new Thread(() -> {
                for (int j = diff * finalI; j < diff * (finalI + 1); j += 1) {};
                latch.countDown();
            });
        }

        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        latch.await();

        long end = System.currentTimeMillis();
        System.out.println("花费时间为：" + (end - start));
    }

    @Test
    public void test02() throws Exception {
        System.out.println("通过2个线程来执行：");
        this.commonHandle(2);

        System.out.println("通过10个线程来执行：");
        this.commonHandle(10);

        System.out.println("通过100个线程来执行：");
        this.commonHandle(100);

        System.out.println("通过1000个线程来执行：");
        this.commonHandle(1000);

        System.out.println("通过10000个线程来执行：");
        this.commonHandle(10000);
    }
}
