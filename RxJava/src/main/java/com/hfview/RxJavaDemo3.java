package com.hfview;


import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/15 09:36
 */
@Slf4j
public class RxJavaDemo3 {

    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(1);
        long start = System.currentTimeMillis();

        final int length  = 100000;
        String[] arr = new String[length];
        for(int i=0;i<length;i++){
            arr[i] = (i+1)+"";
        }

        //创建被观察者---方式2
        Flowable.fromArray(arr)
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;
                    private int count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        log.debug("     观察者调用onSubscribe"+Thread.currentThread().getName());
                        subscription = s;
                        s.request(10);
                    }

                    @Override
                    public void onNext(String s) {
                        count++;
                        //sleep(200);
                        log.debug("     观察者接受数据onNext:"+s+"  "+Thread.currentThread().getName());

                        if(count!=0&&count%10==0){
                            log.debug("观察者再次请求数据10条,当前已经处理了:"+count+"条");
                            subscription.request(10);
                        }
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
