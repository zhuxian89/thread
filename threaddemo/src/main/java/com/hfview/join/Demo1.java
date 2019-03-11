package com.hfview.join;

/**
 * join:等待线程去死亡
 * @author: zhw
 * @since: 2019/3/11 17:16
 */
public class Demo1 {

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    int value = (int) (Math.random()*10000);

                    System.out.println(value);

                    Thread.sleep(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        thread.join();//内部调用wait。所以会释放锁

        System.out.println("主线程结束");
    }

}
