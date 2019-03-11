package com.hfview.threadSignal.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/11 16:43
 */
public class Main {

    public static void main(String[] args) throws Exception {

        WriteDta writeDta = new WriteDta();
        ReadData readData = new ReadData();

        PipedOutputStream out  =new  PipedOutputStream();
        PipedInputStream  in = new PipedInputStream();
        out.connect(in);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                writeDta.writeMethod(out);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readData.readMethod(in);
            }
        });

        t2.start();
        t1.start();
    }

}
