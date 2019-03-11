package com.hfview.threadSignal.exercise;

import org.omg.CORBA.OBJ_ADAPTER;

/**
 * 5个线程备份到A数据库
 * 5个线程备份到B数据库
 * 备份A数据和备份B数据交叉进行
 *
 * @author: zhw
 * @since: 2019/3/11 16:57
 */
public class Demo1 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        Thread a1 = new BackupA(shareData);
        Thread a2 = new BackupA(shareData);
        Thread a3 = new BackupA(shareData);
        Thread a4 = new BackupA(shareData);
        Thread a5 = new BackupA(shareData);


        Thread b1 = new BackupB(shareData);
        Thread b2 = new BackupB(shareData);
        Thread b3 = new BackupB(shareData);
        Thread b4 = new BackupB(shareData);
        Thread b5 = new BackupB(shareData);

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();

        b1.start();
        b2.start();
        b3.start();
        b4.start();
        b5.start();

    }


}

class ShareData{

    public Object lock = new Object();

    public boolean isBackupA = true;

}

class BackupA extends Thread{

    private ShareData shareData;

    public BackupA(ShareData shareData){
        this.shareData = shareData;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (shareData.lock){

                while(!shareData.isBackupA){
                    shareData.lock.wait();
                }

                System.out.println(Thread.currentThread().getName()+":备份了数据库A");

                shareData.isBackupA = false;
                shareData.lock.notifyAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class BackupB extends Thread{

    private ShareData shareData;

    public BackupB(ShareData shareData){
        this.shareData = shareData;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (shareData.lock){

                while(shareData.isBackupA){
                    shareData.lock.wait();
                }

                System.out.println(Thread.currentThread().getName()+":备份了数据库B");

                shareData.isBackupA = true;
                shareData.lock.notifyAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}