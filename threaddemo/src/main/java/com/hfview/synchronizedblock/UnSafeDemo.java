package com.hfview.synchronizedblock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhw
 * @Date: 2019/3/4 09:41
 *
 * 同步方法的缺点很明显，就是不高效，只要一个方法占用了锁，其他方法就会阻塞。
 * 同步代码块的引入可以部分解决这个问题，让只会出现线程安全的代码进行同步，让费时，但不影响线程安全的代码异步操作。
 * 所以在试用同步代码块的方法中会存在部分同步，部分异步的情况。如果同步代码块结合if判断，很有可能出现脏读问题。
 * 下面例子开始演示
 */
@Slf4j
public class UnSafeDemo {


    @Test
    public void test1(){
        final MyService<String> myService = new MyService<String>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myService.add("aaa");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myService.add("bbb");
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(myService.size());
    }

    //只能存一个元素
    @Data
    private class MyService<T>{

        private  List<T> list = new ArrayList<>();

        public synchronized int size(){
            return list.size();
        }

        public synchronized void doAdd(T t){
            list.add(t);
        }

        public void add(T t){
            log.debug(" enter into method add ");

            try {
                if(size()<1){
                    Thread.sleep(2000);
                    doAdd(t);
                }else{
                    log.debug("the list can only set one element");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



}
