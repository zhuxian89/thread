package com.hfview.list;

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/13 14:04
 */
@Slf4j
public class FailFase {

    static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws Exception{



        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Iterator<Integer> it = list.iterator();
                log.debug("iterator method it.hasNext():"+it.hasNext());
                while(it.hasNext()){
                    log.debug("iterator method:");
                    System.out.println(it.next());
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    list.add((i+1));
                }
                log.debug(" add method end");
            }
        });

        executorService.shutdown();
    }
}
