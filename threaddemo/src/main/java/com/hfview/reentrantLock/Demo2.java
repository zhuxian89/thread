package com.hfview.reentrantLock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 修改多消费者和生产者
 *
 * @author: zhw
 * @since: 2019/3/12 11:50
 */
public class Demo2 {


    public static void main(String[] args) {

        Service service = new Service();


        for(int i=0;i<20;i++){
            Produce p = new Produce(service);

            Consumer c = new Consumer(service);

            p.start();
            c.start();
        }


    }


}

class Service{

    private ReentrantLock lock = new ReentrantLock();

    private Condition pCondition = lock.newCondition();

    private Condition sCondition = lock.newCondition();

    private List<String> list = new ArrayList<>();

    public void push(){
        try {
            lock.lock();
            while(list.size()>0){
                pCondition.await();
            }

            list.add("zhw");

            System.out.println("push zhw :"+Thread.currentThread().getName());

            sCondition.signal();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void pop(){
        try {
            lock.lock();
            while(list.size()==0){
                sCondition.await();
            }

            list.remove(0);

            System.out.println("        pop zhw :"+Thread.currentThread().getName());

            pCondition.signal();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

class Produce extends Thread{

    private Service service;

    public Produce(Service service){
        this.service = service;
    }

    @Override
    public void run(){
        service.push();
    }
}

class Consumer extends  Thread{

    private Service service;

    public Consumer(Service service){
        this.service = service;
    }

    @Override
    public void run(){
        service.pop();
    }

}