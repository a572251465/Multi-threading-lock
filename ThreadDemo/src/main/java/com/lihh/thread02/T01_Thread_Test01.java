package com.lihh.thread02;

public class T01_Thread_Test01 {
    static int i = 0;
    public static void main(String[] args) {
        synchronized (T01_Thread_Test01.class) {
            i ++;
        }
    }
}
