package com.hfview.synchronizedblock;

import lombok.extern.slf4j.Slf4j;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 11:18
 */
@Slf4j
public class InnerStaticClass {


    public static void main(String[] args) {

        final A a = new A();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.method1();
            }
        },"thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.method2();
            }
        },"thread2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
               A.method3();
            }
        },"thread3");

        thread1.start();
        //thread2.start();
        thread3.start();

    }


    public static class A{

        public synchronized  void method1(){
            log.debug("enter into method1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("leave from method1");
        }

        public  void method2(){

            synchronized (A.class){
                log.debug("enter into method2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("leave from method2");
            }
        }

        public synchronized static void method3(){
            log.debug("enter into method3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("leave from method3");
        }

    }

}
