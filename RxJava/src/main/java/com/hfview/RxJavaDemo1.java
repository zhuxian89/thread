package com.hfview;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/15 09:36
 */
@Slf4j
public class RxJavaDemo1 {

    public static void main(String[] args) {

        //创建被观察者---方式1
        Observable<String> switcher1 = Observable.create(emitter->{
            log.debug("Observable emit 1");
            emitter.onNext("1");

            log.debug("Observable emit 2");
            emitter.onNext("2");

            log.debug("Observable emit 3");
            emitter.onNext("3");

            log.debug("Observable emit 4");
            emitter.onNext("4");

            log.debug("Observable emit onComplete ");
            emitter.onComplete();
        });

        //创建被观察者---方式2
        //Observable<String> switcher2 = Observable.just("on","off","on","on");

        //创建被观察者---方式3
        //String [] kk={"On","Off","On","On"};
        //Observable<String> switcher3 =Observable.fromArray(kk);


        //创建观察者
        switcher1.subscribe(new Observer<String>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                log.debug("     观察者onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                log.debug("     观察者onNext:"+s);
                if(s.equals("2")){
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                log.error("     观察者onError",e);
            }

            @Override
            public void onComplete() {
                log.info("      观察者onComplete");
            }
        });

    }

}
