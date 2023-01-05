package com.lihh.thread02;

public class T06_Thread_ThreadLocal {

    static ThreadLocal t1 = new ThreadLocal();
    static ThreadLocal t2 = new ThreadLocal();

    public static void main(String[] args) {
        t1.set(123);
        t2.set(456);
        t1.set(345);

        new Thread(() -> {
            System.out.println("线程:" + t1.get());
            System.out.println("线程" + t2.get());
        }).start();

        System.out.println("main线程" + t1.get());
        System.out.println("main线程" + t2.get());
    }
}
