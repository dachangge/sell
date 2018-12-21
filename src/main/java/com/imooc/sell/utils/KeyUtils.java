package com.imooc.sell.utils;

import java.util.Random;

public class KeyUtils {
    public static synchronized String UniqueKey(){
        Random random = new Random();
        return System.currentTimeMillis() + String.valueOf(random.nextInt(90000) + 10000) ;
    }
}
