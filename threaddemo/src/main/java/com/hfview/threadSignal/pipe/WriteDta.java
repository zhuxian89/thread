package com.hfview.threadSignal.pipe;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/11 16:33
 */
public class WriteDta {

    public void writeMethod(PipedOutputStream out){
        try {
            System.out.println("WriteDta:");
            for(int i=0;i<30;i++){
                String outData = (i+1)+"";
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            out.close();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
