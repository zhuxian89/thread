package com.hfview.reentrantReadWriteLock.read;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/12 15:08
 */
public class Demo1 {

    public static void main(String[] args) {

        Service service = new Service();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.read1();
                //service.write1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //service.read2();
                service.write2();
            }
        });

        t1.start();
        t2.start();

    }


}






class Service {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read1() {
        try {

            /**
             * 共享读锁
             */
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+" 获得read锁 "+System.currentTimeMillis());

            Thread.sleep(2000);
        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }
    }

    public void read2() {
        try {

            lock.readLock().lock();

            System.out.println(Thread.currentThread().getName()+" 获得read锁 "+System.currentTimeMillis());

            Thread.sleep(2000);
        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }
    }


    public void write1() {
        try {

            lock.writeLock().lock();

            System.out.println(Thread.currentThread().getName()+" 获得write锁 "+System.currentTimeMillis());

            Thread.sleep(2000);
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
        }
    }

    public void write2() {
        try {

            lock.writeLock().lock();

            System.out.println(Thread.currentThread().getName()+" 获得write锁 "+System.currentTimeMillis());

            Thread.sleep(2000);
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
        }
    }
}