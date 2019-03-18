package com.hfview.stream;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/18 11:22
 */
public class StreamDemo1 {

    public static void main(String[] args) {

        Stream<String> stream = Stream.of("a","b","c","d");

        //过滤
        List<String> list1 =  stream.filter(v->{
            return !v.equals("d");
        }).collect(Collectors.toList());
        System.out.println(list1);




    }


}
