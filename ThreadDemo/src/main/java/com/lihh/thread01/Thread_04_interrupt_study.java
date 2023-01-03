package com.lihh.thread01;

public class Thread_04_interrupt_study {
    public static void main(String[] args) throws InterruptedException {
        testInterrupt02();
    }

    public static void testInterrupt02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (;;) {
                if (Thread.interrupted()) {
                    System.out.println("此线程被标记了，并且清除了标记");
                    break;
                }
                System.out.println("这是一个子线程:" + Thread.currentThread().getName());
            }
        });

        t1.start();

        Thread.sleep(1000);

        t1.interrupt();

        Thread.sleep(1000);

        System.out.println("是否还有状态:" + t1.isInterrupted());
    }

    public static void testInterrupt01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (;;) {
                // 判断线程是否被标记中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("此进程被标记了");
                    break;
                }
                System.out.println("这是一个子进程：" + Thread.currentThread().getName());
            }
        });

        t1.start();

        Thread.sleep(1000);

        // 此时对线程进行标记
        t1.interrupt();
    }
}
