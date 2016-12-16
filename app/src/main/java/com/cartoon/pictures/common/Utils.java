package com.cartoon.pictures.common;

import java.util.Random;

/**
 * Created by chenxunlin01 on 2016/12/6.
 */
public class Utils {

    public static final int[] marks = {
            0xff420042, 0xffF23907, 0xff00FF84, 0xffFF8484, 0xff008484, 0xffB6B34B, 0xff50B248, 0xffC95B7, 0xffF4DF06
    };

    public static int getMarkColor(int position) {
        int count = marks.length;
        if (position < 0 || position >= count) {
            Random random = new Random();
            position = random.nextInt(9);
        }
        return marks[position];
    }
}
