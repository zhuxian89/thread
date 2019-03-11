package com.hfview.threadSignal.producerConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/11 15:45
 */
public class MyStack {

    private List<String> list= new ArrayList<>();

    synchronized void push(){
        try{

            while (list.size()>0){//此处不能用if(因为被notify的线程无需再次获取锁)
                this.wait();
            }

            list.add("anyString="+Math.random());
            this.notifyAll(); //防止假死，这里使用all
            System.out.println("push method:"+Thread.currentThread().getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    synchronized void pop(){
        try{

            while(list.size()==0){
                System.out.println("pop "+Thread.currentThread().getName()+" wait");
                this.wait();
            }

            list.remove(0);
            this.notifyAll();//此处不能用
            System.out.println("pop method:"+Thread.currentThread().getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MyStack myStack = new MyStack();

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
