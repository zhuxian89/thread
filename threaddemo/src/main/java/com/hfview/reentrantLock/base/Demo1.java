package com.hfview.reentrantLock.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的基本使用
 *
 * @author: zhw
 * @since: 2019/3/12 11:41
 */
public class Demo1 {

    public static void main(String[] args) {

        final ReentrantLock lock = new ReentrantLock();

         new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    System.out.println("a begin");



                    System.out.println("a end");

                    while(true){

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    System.out.println("b begin");

                    Thread.sleep(2000);

                    System.out.println("b end");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();

    }

}
