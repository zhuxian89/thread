package com.hfview.deadlock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 10:19
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        final A a = new A();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.setFlag("a");
                a.run();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.setFlag("b");
                a.run();
            }
        });

        thread1.start();
        thread2.start();

    }


    @Data
    public static class A {

        private String flag = "";

        private Object lock1 = new Object();
        private Object lock2 = new Object();

        public void method1(){
            try {
                synchronized (lock1){
                    log.debug("get lock1 ");
                    Thread.sleep(2000);

                    synchronized (lock2){
                        log.debug("get lock1 and lock2 ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void method2(){
            try {
                synchronized (lock2){
                    log.debug("get lock2 ");
                    Thread.sleep(2000);

                    synchronized (lock1){
                        log.debug("get lock2 and lock1 ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            if(flag.equals("a")){
                method1();
            }else if(flag.equals("b")){
                method2();
            }
        }
    }


}
