package com.hfview.join;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/12 09:55
 */
public class ThreadB extends Thread{

    @Override
    public void run() {
        try {
            super.run();
            synchronized (this){

                System.out.println("begin B threadName="+Thread.currentThread().getName()+" "+System.currentTimeMillis());

                sleep(5000);

                System.out.println("end B threadName="+Thread.currentThread().getName()+" "+System.currentTimeMillis());


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
