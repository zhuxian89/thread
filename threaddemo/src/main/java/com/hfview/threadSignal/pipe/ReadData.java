package com.hfview.threadSignal.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.nio.charset.Charset;

/**
 *
 * @author: zhw
 * @since: 2019/3/11 16:36
 */
public class ReadData {

    public void readMethod(PipedInputStream in){

        try{
            System.out.println("ReadData:");

            byte[] byteArray = new byte[20];

            int length = in.read(byteArray);//这里如果没有数据会wait操作
            while(length!=-1){
                String  data = new String(byteArray,0,length);
                System.out.print(data);
                length = in.read(byteArray);
            }
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
