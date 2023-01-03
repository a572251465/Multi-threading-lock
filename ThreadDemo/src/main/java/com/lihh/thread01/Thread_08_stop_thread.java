package com.lihh.thread01;

public class Thread_08_stop_thread {

    public static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        testStop04();
    }

    public static void testStop04() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!Thread.interrupted()) {
            }
            System.out.println(
                    "执行当前线程" + Thread.currentThread().getName()
            );
        });
        t1.start();

        Thread.sleep(2000);

        t1.interrupt();
    }

    public static void testStop03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Integer count = 0;
            while (running) {
                if (count >= 10) {
                    running = false;
                }
                try {
                    Thread.sleep(100);
                    count ++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("同步 执行结束");
        });

        t1.start();

        Thread.sleep(2000);
    }

    // 使用stop 强制关闭线程
    public static void testStop01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("这是一个线程" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();

        Thread.sleep(2000);

        t1.stop();
    }

    public static void testStop02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
           while (true) {
               System.out.println("这是一个线程：" + Thread.currentThread().getName());
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        t1.start();

        Thread.sleep(2000);

        // 表示暂停
        t1.suspend();
        Thread.sleep(2000);
        // 表示开始
        t1.resume();
    }
}
