package com.hfview;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/15 15:54
 */
@Slf4j
public class RxJavaDemo4 {

    public static void main(String[] args) throws Exception{

        long start =  System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Observable observable1 = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("被观察者生成字符串1");
                sleep(3000);
                return "1";
            }
        }).subscribeOn(Schedulers.newThread());

        Observable observable2 = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("被观察者生成字符串2");
                sleep(3000);
                return "2";
            }
        }).subscribeOn(Schedulers.newThread());;

        Observable observable3 = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("被观察者生成字符串3");
                sleep(3000);
                return "3";
            }
        }).subscribeOn(Schedulers.newThread());;


        //Observable<String> unionObservable = observable1.concatWith(observable2).concatWith(observable3);
        Observable<String> unionObservable = observable1.mergeWith(observable2).mergeWith(observable3);


        unionObservable
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
               log.debug("      观察者获取数据:"+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });


        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.debug("主线程结束了,耗时:"+(end-start));

    }


    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
