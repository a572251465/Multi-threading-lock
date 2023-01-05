package com.lihh.thread02;

import java.util.concurrent.atomic.AtomicStampedReference;

public class T04_Thread_AtomicStampedReference {
    public static void main(String[] args) {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("AAA", 1);

        String oldValue = reference.getReference();
        Integer oldVersion = reference.getStamp();

        boolean b = reference.compareAndSet(oldValue, "B", oldVersion, oldVersion + 1);
        System.out.println("修改1版本的：" + b);

        boolean c = reference.compareAndSet("B", "C", 1, 1 + 1);
        System.out.println("修改2版本的：" + c);
    }
}
