package com.hfview.join;

/**
 * 验证
 *
 * @author: zhw
 * @since: 2019/3/12 09:56
 */
public class Main
{

    public static void main(String[] args) throws Exception{

        ThreadB b = new ThreadB();

        ThreadA a = new ThreadA(b);

        a.start();
        b.start();

        b.join(2000);

        System.out.println("          main end   "+System.currentTimeMillis());
    }

}
