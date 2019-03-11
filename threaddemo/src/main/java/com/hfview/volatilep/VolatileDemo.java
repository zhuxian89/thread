package com.hfview.volatilep;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 11:43
 */
@Slf4j
public class VolatileDemo {

    public static void main(String[] args) throws Exception {

        A a = new A();
        a.start();

        Thread.sleep(3000);

        a.setCotinue(false);

        log.debug("线程已经结束");
    }

    @Data
    private static class A extends Thread{

        private volatile  boolean isCotinue = true;


        @Override
        public void run() {
            super.run();

            while(isCotinue){
                log.debug("");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("线程已经结束");
        }
    }


}
