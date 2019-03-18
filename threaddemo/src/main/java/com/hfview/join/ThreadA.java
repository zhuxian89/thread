package com.hfview.join;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/12 09:52
 */
public class ThreadA extends Thread{

    private ThreadB threadB;

    public ThreadA(ThreadB threadB){
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try {
            super.run();

            synchronized (threadB){
                System.out.println("begin A threadName="+Thread.currentThread().getName()+" "+System.currentTimeMillis());

                sleep(5000);

                System.out.println("end A threadName="+Thread.currentThread().getName()+" "+System.currentTimeMillis());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
