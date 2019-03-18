package com.hfview.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/14 09:38
 */
@Slf4j
public class ListIteratorDemo {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        /*list.add("zhw1");
        list.add("zhw2");
        list.add("zhw3");


        ListIterator<String> lit = list.listIterator(list.size());
        while(lit.hasPrevious()){
            String value = lit.previous();
            //String preValue = lit.previous();

            log.debug("next():"+value);
            //log.info("previous():"+preValue);
        }*/

        for(String value:list){
            System.out.println(value);
        }


    }


}
