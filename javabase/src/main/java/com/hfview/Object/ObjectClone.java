package com.hfview.Object;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 写注释
 *
 * @author: zhw
 * @since: 2019/3/4 16:26
 */
@Slf4j
public class ObjectClone {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        list1.add("zhw");

        List<String> list2 = (List<String>)((ArrayList<String>) list1).clone();

        System.out.println(list1==list2);

        System.out.println(list1.equals(list2));

        log.debug(""+list1.getClass());

    }

}
