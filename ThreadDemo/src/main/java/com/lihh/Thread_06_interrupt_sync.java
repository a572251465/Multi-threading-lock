package com.lihh;

public class Thread_06_interrupt_sync {

    private static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        test01();
    }

    public static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("wo shi t1");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
            System.out.println("wo shi t2");
        });

        t2.start();

        Thread.sleep(1000);

        t2.interrupt();
    }
}
