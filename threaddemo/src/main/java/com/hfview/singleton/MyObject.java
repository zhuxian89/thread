package com.hfview.singleton;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/12 15:40
 */
public class MyObject {

    private  static MyObject myObject;

    private MyObject(){

    }

    public static MyObject getNewInstance(){

        if(myObject==null){

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (MyObject.class){

                if(myObject==null){
                    myObject = new MyObject();

                }
            }

        }

        return myObject;
    }


    public static void main(String[] args) {

        ThreadA t1 = new ThreadA();
        ThreadA t2 = new ThreadA();
        ThreadA t3 = new ThreadA();

        t1.start();
        t2.start();
        t3.start();
    }
}

class ThreadA extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println(MyObject.getNewInstance().hashCode());
    }
}