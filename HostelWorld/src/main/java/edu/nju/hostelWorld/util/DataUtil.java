package edu.nju.hostelWorld.util;

import java.util.Random;

/**
 * Created by 张文玘 on 2017/3/4.
 */
public class DataUtil {

    public static int NOT_ACTIVITED = 0;
    public static int ACTIVITED = 1;
    public static int PAUSE = 2;
    public static int STOP = 3;

    public static boolean isPhoneNum(String phoneNumber){
        boolean isPhoneNum = false;
        return isPhoneNum;
    }

    public static int getRandomId(){
        int min = 1000000;
        int max = 9999999;
        Random rand = new Random();
        int result = rand.nextInt(max)%(max-min+1)+min;
        return result;

    }
}
