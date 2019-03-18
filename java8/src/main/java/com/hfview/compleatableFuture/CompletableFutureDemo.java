package com.hfview.compleatableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class CompletableFutureDemo {


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //basic();
        //combine();

        combine2();

        long end = System.currentTimeMillis();
        System.out.println("共花费:"+(end-start));
    }




    /**
     * 结果联合
     */
    public static void combine() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture
                .supplyAsync(()->{
                    log.debug("supplyAsync1");
                    sleep(2000);
                    return "zhw";
                })
                .thenCombineAsync(CompletableFuture.supplyAsync(()->{
                    log.debug("supplyAsync2");
                    sleep(2000);
                    return " hellow ";
                }),(s1,s2)->{
                    log.debug("thenCombineAsync");
                    return s1+s2;
                })
        .thenAccept(x->{
            log.debug("thenAccept");
            System.out.println(x);
            countDownLatch.countDown();
        });

        countDownLatch.await();
        System.out.println("main 线程结束");
    }

    /**
     * 结果联合
     */
    public static void combine2() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture completableFuture1 = CompletableFuture
                .supplyAsync(()->{
                    log.debug("supplyAsync1");
                    sleep(2000);
                    return "zhw";
                });
        CompletableFuture completableFuture2 =CompletableFuture.supplyAsync(()->{
                    log.debug("supplyAsync2");
                    sleep(2000);
                    return " hellow ";
                });

        CompletableFuture completableFuture3 =CompletableFuture.supplyAsync(()->{
            log.debug("supplyAsync3");
            sleep(2000);
            return " word ";
        });

        List<CompletableFuture<String>> list = Arrays.asList(completableFuture1,completableFuture2,completableFuture3);

        CompletableFuture<List<String>> all = sequence(list);

        all.whenComplete((v,e)->{
            System.out.println(v);
            countDownLatch.countDown();
        });


        countDownLatch.await();
        System.out.println("main 线程结束");
    }


    /**
     * 基本用法
     */
    public static void basic()throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture.supplyAsync(()->{
            sleep(60000);
            System.out.println("线程:"+Thread.currentThread().getName()+"调用了supplyAsync");
            return "zhw";
        }).thenApplyAsync(x->{
            System.out.println("线程:"+Thread.currentThread().getName()+"调用了thenApply");
            countDownLatch.countDown();
            return x+" hellow word";
        });

        countDownLatch.await();
        System.out.println("main 线程结束");
    }


    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void costTime(){
        int i=0;
        while(i<100000){
            i++;
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

}
