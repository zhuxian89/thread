package com.hfview.threadSignal.waitNotify;

import org.apache.poi.ss.formula.functions.T;

/**
 * 等待通知机制
 *
 * @author: zhw
 * @since: 2019/3/11 14:25
 */
public class Student {


    public static void main(String[] args)  throws Exception{

        final Object lock = new Object();

        Thread student1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("student1 start sleep");

                    try {
                        lock.wait();//立刻释放锁
                    } catch (InterruptedException e) {
                        System.out.println("student1 is interrupted");
                    }
                    System.out.println("student1 end sleep");

                    System.out.println(" student1 start study");
                }
            }
        });

        Thread student2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("student2 start sleep");

                    try {
                        lock.wait();//立刻释放锁
                    } catch (InterruptedException e) {
                        System.out.println("student2 is interrupted");
                    }
                    System.out.println("student2 end sleep");

                    System.out.println(" student2 start study");
                }
            }
        });

        Thread deskmate = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("deskmate watch teaching");

                    lock.notify();//在代码块或者同步方法 结束后才释放锁，注意打印顺序

                    System.out.println("deskmate haved nofify student");
                }
            }
        });

        student1.setPriority(10);

        student1.start();
        student2.start();

        Thread.sleep(3000);

        deskmate.start();

        Thread.sleep(3000);

        student2.interrupt();

    }



}
