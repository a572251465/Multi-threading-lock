package com.lihh.thread02;

import java.util.concurrent.locks.ReentrantLock;

public class T05_Thread_ReentrantLock {

    static ReentrantLock lock = new ReentrantLock();
    private static int count = 0;

    public static void add() {
        try {
            lock.lock();
            for (int i = 0; i < 100000; i++) {
                count ++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t01 = new Thread(T05_Thread_ReentrantLock::add);
        Thread t02 = new Thread(T05_Thread_ReentrantLock::add);

        t01.start();
        t02.start();

        t01.join();
        t02.join();

        System.out.println(count);
    }
}
