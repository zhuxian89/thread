package com.hfview;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/15 09:36
 */
@Slf4j
public class RxJavaDemo2 {

    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(1);
        long start = System.currentTimeMillis();

        //创建被观察者---方式2
        Observable
                .just("11011","21011","31011","41011")
                //.subscribeOn(Schedulers.newThread())//被观察者线程切换
                //.observeOn(Schedulers.io())
                    .map(s->{
                        log.debug("map操作当前线程为："+Thread.currentThread().getName());
                        return convert(s);//转换功能
                    })
                    /*.flatMap(new Function<String, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(String s) throws Exception {
                            return Observable.fromArray(s.split("0"));
                        }
                    })*/
                    .observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        log.debug("     观察者调用onSubscribe:"+Thread.currentThread().getName());
                        disposable = d;
                    }

                    @Override
                    public void onNext(String s) {
                        sleep(2000);
                        log.debug("     观察者接受数据onNext:"+s+"  "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        log.error("     观察者调用onError"+Thread.currentThread().getName(),e);
                    }

                    @Override
                    public void onComplete() {
                        log.info("      观察者调用onComplete"+Thread.currentThread().getName());
                        countDownLatch.countDown();
                    }
                });

        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.debug("主线程结束了,耗时:"+(end-start));
    }

    /**
     * 模拟耗时操作
     * @param s
     * @return
     */
    public static String convert(String s){
        //sleep(2000);
        return s+"1";
    }

    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
