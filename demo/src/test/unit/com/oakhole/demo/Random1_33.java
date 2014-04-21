package com.oakhole.demo;

import org.junit.Test;

import java.util.*;

/**
 * Created by oakhole on 2014-03-27 01:19.
 */
@SuppressWarnings("ALL")
public class Random1_33 {

    public void random() {

        //1-33的自然数中随机选取6个数字，要求算法高效可行

        List<Integer> randomArray = new ArrayList<Integer>();

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < 33; i++) {
            randomArray.add(i);
        }

        for (int i = 32; i > 26; i--) {
            int randomInteger = (int) (Math.random() * i + 1);
            result.add(randomArray.get(randomInteger));
            randomArray.remove(randomInteger);
        }

        //进行内部排序
        Collections.sort(result);

        result.add((int) (Math.random() * 16 + 1));

        System.out.println(result.toString());
    }

    @Test
    public void generateRandom() {
       random();
    }

}
