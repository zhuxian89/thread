package com.hfview.volatilep;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 15:14
 */
public class AtomicDemo {


    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();

        ai.addAndGet(1);

    }


}
