package com.hfview.threadSignal.waitNotify;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/12 10:32
 */
public class WaitDemo {


    public static void main(String[] args) {

        final Object lock = new Object();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock){
                        System.out.println("A begin ………………………………");

                        lock.wait(2000);

                        System.out.println("A end ………………………………");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock){
                        System.out.println("B begin ………………………………");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        a.start();

        b.start();


    }


}

