package com.hfview.threadSignal.waitNotify;


class Service implements Runnable{
    private Object lock;

    private String username ="a";
    private String psw ="aa";

    public Service(Object lock){
        this.lock = lock;
    }

    public void doService()  {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+" begin");

            try {
                username = Thread.currentThread().getName()+"aa";
                lock.wait();
                //暂停之后没有重新获取锁
                psw = Thread.currentThread().getName()+"aaa";
                //Thread.sleep(2000);
                System.out.println("username:"+username+",psw="+psw);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" end");
        }
    }

    @Override
    public void run() {
        doService();
    }
}

/**
 * 等待通知机制
 *
 * @author: zhw
 * @since: 2019/3/11 14:25
 */
public class NotifyAllDemo {


    public static void main(String[] args)  throws Exception{
        final Object lock = new Object();
        Service service = new Service(lock);

        Thread t1 = new Thread(service);
        Thread t2 = new Thread(service);
        Thread t3 = new Thread(service);

        t1.start();
        t2.start();
        t3.start();

        Thread notifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        });
        notifyThread.start();
    }

}
