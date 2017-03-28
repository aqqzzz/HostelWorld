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

    //订单状态
    public static byte RESERVE = 1;//已预订
    public static byte RESERVE_CANCEL = 0; //已取消
    public static byte CHECK_IN = 2; //已入住
    public static byte CHECK_OUT = 3; //已离店

    //申请结算的状态
    public static byte WAIT_SETTLEMENT = 0; //正在等待
    public static byte HAVE_SETTLEMENT = 1; //已结算

    //结算原因
    public static byte REASON_CANCEL = 0; //客户取消订单
    public static byte REASON_FINISHED = 1; //交易完成（已入住），向客栈结算钱款

    //付款方式
    public static byte PAY_BY_CARD = 0; //使用会员卡付款
    public static byte PAY_BY_CASH = 1; //使用现金付款

    //入住登记类型
    public static byte CHECKIN_VIP = 0; //会员登记入住
    public static byte CHECKIN_NOVIP = 1; //非会员登记入住

    //消费记录类型
    public static byte PAY_IN = 0; //收入
    public static byte PAY_OUT = 1; //支出

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
