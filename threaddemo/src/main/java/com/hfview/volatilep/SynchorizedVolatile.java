package com.hfview.volatilep;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.internal.runners.statements.RunAfters;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 15:33
 */
public class SynchorizedVolatile {

    public static void main(String[] args) throws Exception {
        Service service = new Service();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.runMethod();
            }
        },"thread1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.stopMethod();
            }
        },"thread2");


        thread1.start();
        Thread.sleep(2000);

        thread2.start();
    }

}

@Data
@Slf4j
class Service{

    private  boolean isContinue = true;

    public  void runMethod(){
        log.debug("enter into runMethod");

        while(isContinue){
        }

        log.debug("exit runMethod");
    }

    public  void stopMethod(){
        log.debug("enter into stopMethod:"+isContinue);
        this.isContinue = false;
        log.debug("exit stopMethod:"+isContinue);
    }



}
