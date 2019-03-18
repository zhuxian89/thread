package com.hfview.list;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/13 11:12
 */
public class MyList extends ArrayList {

    @Override
    public synchronized boolean add(Object o) {
        return super.add(o);
    }


    public static void main(String[] args) throws Exception{

        MyList list = new MyList();
        //CopyOnWriteArrayList list = new CopyOnWriteArrayList();


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        long s = System.currentTimeMillis();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<500000;i++){
                    list.add(i);
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<500000;i++){
                    list.add(i);
                }
            }
        });



        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS);


        long e = System.currentTimeMillis();
        System.out.println("花费了:"+(e-s)+"  list.size:"+list.size());
    }


    @Test
    public void test1(){
        ArrayList list = new ArrayList();

        list.add("1");
        list.add("1");
        list.add("1");

        list.remove(0);

    }

}
