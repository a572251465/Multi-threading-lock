package com.lihh.thread01;

public class Thread_05_interrupt_sleep_wait {

    public static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        test02();
    }

    public static void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (o) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        System.out.println("遇到异常了  重新执行");
                    }
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

    public static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("sleep 被唤醒可以继续执行了");
                }
                if (Thread.interrupted()) {
                    System.out.println("我是被标记的线程:" + Thread
                            .currentThread().getName());
                }
            }
        });

        t1.start();

        Thread.sleep(1000);

        t1.interrupt();
    }
}
