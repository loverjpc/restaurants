package com.jipengcheng.commont.util;

import java.util.Random;

public class RandomUtil {
    public static String getRandom(int num){
        String rs="";
        Random random = new Random();
        for (int i=0;i<num;i++){
            rs+=String.valueOf(random.nextInt(10));
        }
        return rs;
    }
}
