package com.hfview.threadSignal.producerConsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/11 15:45
 */
@Slf4j
public class MyStack2 {

    private List<String> list= new ArrayList<>();

    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(5);

    void push(){
        try{
                queue.put("zhw");
                log.debug("生产者"+Thread.currentThread().getName()+"生产了一个:"+queue.size());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void pop(){
        try{
            queue.take();
            log.debug("    消费者"+Thread.currentThread().getName()+"消费了一个:"+queue.size());
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MyStack2 myStack = new MyStack2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.push();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    myStack.push();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.pop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.pop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.pop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.pop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                myStack.pop();
            }
        }).start();

    }


}
