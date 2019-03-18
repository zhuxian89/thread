package com.hfview.reentrantLock.Fair;


import java.lang.invoke.LambdaConversionException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁
 * @author: zhw
 * @since: 2019/3/12 14:18
 */
public class Service implements Runnable{

    private final ReentrantLock lock;

    public Service(boolean isFair){
        lock = new ReentrantLock(isFair);
    }

    @Override
    public void run(){

        try {
            System.out.println("线程:"+Thread.currentThread().getName()+" 运行了");

            lock.lock();

            System.out.println("    线程:"+Thread.currentThread().getName()+" 获得了锁");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        Service service = new Service(true);

        Thread[] arr = new Thread[20];

        for(int i=0;i<20;i++){
            arr[i] = new Thread(service);
        }

        for(Thread t:arr){
            t.start();
        }
    }
}
