package com.hfview.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/13 15:47
 */
public class Demo1 {

    public static void main(String[] args) throws Exception{

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);

        for (int i = 0; i < 10; ++i){
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        startSignal.countDown();      // let all threads proceed

        doneSignal.await();           // wait for all to finish
        System.out.println("main 结束了");

    }

}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
        System.out.println(Thread.currentThread().getName()+":doWork");
    }
}