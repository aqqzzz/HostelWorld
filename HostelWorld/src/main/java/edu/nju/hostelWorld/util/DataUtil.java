package edu.nju.hostelWorld.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张文玘 on 2017/3/4.
 */
public class DataUtil {

    //会员状态
    public static byte NOT_ACTIVITED = 0;
    public static byte ACTIVITED = 1;
    public static byte PAUSE = 2;
    public static byte STOP = 3;

    //银行卡初始金额
    public static double INITIAL_BALANCE=1000000000;

    //审批状态
    public static byte WAIT = 0;
    public static byte APPROVED = 1;//通过审批
    public static byte NOT_APPROVED = 2;//未通过审批

    //申请类型
    public static byte CREATE = 0; //开店申请
    public static byte EDIT = 1; //修改店铺信息申请


    public static boolean isPhoneNum(String phoneNumber){
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static int getRandomId(){
        int min = 1000000;
        int max = 9999999;
        Random rand = new Random();
        int result = rand.nextInt(max)%(max-min+1)+min;
        return result;

    }
}
