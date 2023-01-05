package com.lihh.thread02;

public class T07_Thread_Test02 {
    private static int count = 0;

    public static void add() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(T07_Thread_Test02::add);

        t1.start();
        Thread.sleep(2000);
        System.out.println(count);
    }
}
