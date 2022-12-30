package com.lihh;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("这是创建线程的一种方式：通过继承来实现");
    }
}

class MyRun implements Runnable {
    @Override
    public void run() {
        System.out.println("这是创建线程的一种方式：通过实现接口来实现");
    }
}

class MyCall implements Callable<String> {

    @Override
    public String call() {
        System.out.println("这是创建线程的一种方式：通过实现接口Callable来完成");
        return "success";
    }
}

public class Thread_02_CreateThread {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        // 线程的创建方式1
        new MyThread().start();
        // 线程的创建方式2
        new Thread(new MyRun()).start();
        // 线程的创建方式3
        new Thread(() -> {
            System.out.println("这是创建线程的一种方式：通过lambda表达式创建");
        }).start();
        // 线程的创建方式4
        FutureTask<String> futureTask = new FutureTask<>(new MyCall());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());

        // 线程的创建方式 5
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            System.out.println("这是创建线程的一种方式：通过线程池来进行创建");
        });

        Future<String> submit = executorService.submit(new MyCall());
        System.out.println(submit.get());
        executorService.shutdown();
    }
}
