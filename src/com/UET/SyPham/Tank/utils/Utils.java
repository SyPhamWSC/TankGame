package com.UET.SyPham.Tank.utils;

import java.util.Random;

/**
 * Created by TooNies1810 on 8/7/16.
 */
public class Utils {
    public static int randomLocation(){
        int index;
        Random rd = new Random();
        index = rd.nextInt(470);
        return index;
    }
}
