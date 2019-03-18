package com.hfview.list;

import java.util.concurrent.CountDownLatch;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/13 15:09
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch cd = new CountDownLatch(10);

        cd.countDown();

        System.out.println(cd.getCount());

    }
}

