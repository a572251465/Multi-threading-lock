package com.lihh.thread01;

public class Thread_09_volatile {

    private static boolean flag = true;
    private static volatile boolean flag1 = true;

    public static void main(String[] args) throws InterruptedException {
        test04();
    }

    public static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (flag) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        Thread.sleep(2000);
        flag = false;
    }

    public static void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
           while (flag1) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        t1.start();
        Thread.sleep(1000);

        flag1 = false;
    }

    static class A {
        boolean running = true;
        void m() {
            System.out.println("程序开始...");
            while (running) {}
            System.out.println("程序结束...");
        }
    }

    static private volatile A a = new A();

    public static void test03() throws InterruptedException {
        new Thread(a::m, "t1").start();
        Thread.sleep(1000);
        a.running = false;
    }

    public static void test04() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (flag) {
                try {
                    System.out.println("执行中...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        flag = false;
    }
}
