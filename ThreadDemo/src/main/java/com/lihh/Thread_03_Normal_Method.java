package com.lihh;

public class Thread_03_Normal_Method {

    public static void main(String[] args) throws Exception {
        testSleep();
        testYield();
        testJoin();
    }


    // 意思就是在自己当前线程加入你调用Join的线程，本线程等待。等调用的线程运行完了，自己再去执行。t1和t2两个线程，在t1的某个点上调用了t2.join,它会跑到t2去运行，t1等待t2运行完毕继续t1运行（自己join自己没有意义）
    public static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("我是线程A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < 10; i++) {
                System.out.println("我是线程B" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }

    // yield 就是当前线程正在执行的时候停止下来进入等待队列(线程处于就绪状态，CPU随时可能拿出来执行)
    // 回到等待队列里在系统的调度算法里头呢还是依然有可能把你刚回去的这个线程拿回来继续执行，
    // 当然，更大的可能性是把原来等待的那些拿出一个来执行，所以yield的意思是我让出一下CPU，后面你们能不能抢到那我不管
    public static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) {
                    System.out.println("A: 此时我抢到了..." + i);
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) {
                    System.out.println("B: 此时我抢到了..." + i);
                    Thread.yield();
                }
            }
        }).start();
    }

    // 使用API【sleep】表示线程进行休眠，当前线程展暂停一段时间让给别的线程执行。唤起的时间由睡眠的时间决定
    public static void testSleep() throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        System.out.println("开始了....");
        // 此处休眠是让主线程进行休眠
        Thread.sleep(1000);
        System.out.println("结束了...");
    }
}
