package com.example.lijuw.myapp;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;


public class ComTools {

    private static final String TAG = "ComTools";

    //特殊时期的处理
    public static boolean USER_FILTER = false;

    public static boolean IS_OPEN_USER = false;


    public static boolean MODE_TEST = false;//是否是测试模式，上线必须修改

    public static final int SORT_DESC = 1;
    public static final int SORT_ASC = 0;

    public static String BET_NORMAL = "1";
    public static String BET_HEMAI = "2";

    public static boolean SUPPORT_COMBINATION = true;


    // Lottery type map BetItem type
    public static final Map<String, Integer> LotteryTypeMap = new HashMap<String, Integer>();

    // Lottery name map BetItem type
    public static final Map<String, Integer> LotteryNameMap = new HashMap<String, Integer>();

    // Lottery type map BetItem type
    public static final Map<String, Integer> LotteryTypeStringIdMap = new HashMap<String, Integer>();

    // 彩种到logo Id的映射
    public static final Map<String, Integer> LotteryTypeLogoIdMap = new HashMap<String, Integer>();

    // Lottery type map BetItem type
    public static final Map<String, Integer> LotteryWay = new HashMap<String, Integer>();


    public static void initLotteryTypeMap() {
        // LotteryTypeMap.put("SSC", BetItem.TYPE_JSSC);
        LotteryTypeMap.put("SEVENSTAR", BetItem.TYPE_QXC);
        LotteryTypeMap.put("SSC", BetItem.TYPE_JSSC);
        LotteryTypeMap.put("WELFARE3D", BetItem.TYPE_3D);
        LotteryTypeMap.put("DLT", BetItem.TYPE_DLT);
        LotteryTypeMap.put("sfzc", BetItem.TYPE_SFC);
        LotteryTypeMap.put("SEVEN", BetItem.TYPE_QLC);
        LotteryTypeMap.put("PL3", BetItem.TYPE_PL3);
        LotteryTypeMap.put("PL5", BetItem.TYPE_PL5);
        LotteryTypeMap.put("JCLQ", BetItem.TYPE_JCLQ);
        LotteryTypeMap.put("JCZQ", BetItem.TYPE_JCZQ);
        LotteryTypeMap.put("DCZC", BetItem.TYPE_ZQDC);
        LotteryTypeMap.put("SSQ", BetItem.TYPE_SSQ);
        LotteryTypeMap.put("tc22to5", BetItem.TYPE_22XUAN5);
        LotteryTypeMap.put("ZQDC", BetItem.TYPE_ZQDC);
        LotteryTypeMap.put("GDEL11T05", BetItem.TYPE_GD11XUAN5);
        LotteryTypeMap.put("EL11T05", BetItem.TYPE_JX11XUAN5);
        LotteryTypeMap.put("sfzc14", BetItem.TYPE_SFC);
        LotteryTypeMap.put("sfzc9", BetItem.TYPE_REN9);
        LotteryTypeMap.put("sfzc", BetItem.TYPE_SFC);
        LotteryTypeMap.put("AHK3", BetItem.TYPE_K3);




        LotteryNameMap.put("时时彩", BetItem.TYPE_JSSC);
        // LotteryNameMap.put("六场半全场",BetItem.TYPE_LCZC);
        LotteryNameMap.put("七星彩", BetItem.TYPE_QXC);
        LotteryNameMap.put("3D", BetItem.TYPE_3D);
        LotteryNameMap.put("大乐透", BetItem.TYPE_DLT);
        // LotteryNameMap.put("胜负彩", BetItem.TYPE_SFC);
        LotteryNameMap.put("七乐彩", BetItem.TYPE_QLC);
        LotteryNameMap.put("排列3", BetItem.TYPE_PL3);
        LotteryNameMap.put("排列5", BetItem.TYPE_PL5);
        LotteryNameMap.put("竞彩篮球", BetItem.TYPE_JCLQ);
        LotteryNameMap.put("竞彩足球", BetItem.TYPE_JCZQ);
        LotteryNameMap.put("双色球", BetItem.TYPE_SSQ);
        // LotteryNameMap.put("22选5", BetItem.TYPE_22XUAN5);
        LotteryNameMap.put("北单", BetItem.TYPE_ZQDC);
        // LotteryNameMap.put("任选9场", BetItem.TYPE_REN9);
        LotteryNameMap.put("广东11选5", BetItem.TYPE_GD11XUAN5);
        LotteryNameMap.put("江西11选5", BetItem.TYPE_JX11XUAN5);
        LotteryNameMap.put("快3", BetItem.TYPE_K3);
        // LotteryNameMap.put("进球彩", BetItem.TYPE_SCZC);

    }

    /**
     * ************ 获取球类彩票组合 *************************
     */
    private static ArrayList<ArrayList<String>> combineList;

    public static ArrayList<ArrayList<String>> combineToItemList(ArrayList a, int n) {
        if (null == a || a.size() == 0 || n <= 0 || n > a.size())
            return null;
        String[] b = new String[n];// 辅助空间，保存待输出组合数
        combineList = new ArrayList<ArrayList<String>>();
        getCombinationArrayList(a, n, 0, b, 0);
        return combineList;
    }

    private static void getCombinationArrayList(ArrayList a, int n, int begin, String[] b, int index) {
        if (n == 0) {// 如果够n个数了，输出b数组
            ArrayList<String> contentList = new ArrayList<String>();
            for (int i = 0; i < index; i++) {
                contentList.add(b[i]);
            }
            // System.out.println(sbuffer.toString());
            combineList.add(contentList);
            return;
        }
        if (begin + n > a.size()) {
            // System.out.println("return");
            return;
        }
        for (int i = begin; i < a.size(); i++) {
            b[index] = a.get(i).toString();
            // System.out.println("b["+index+"]"+ ":"+b[index]);
            // System.out.println("("+(n-1)+"  "+ (i+1) +"   "+ (index+1) +")");
            getCombinationArrayList(a, n - 1, i + 1, b, index + 1);
        }
    }

    private static ArrayList<String> combineToStringList;

    public static ArrayList<String> combineToItemString(ArrayList a, int n) {
        if (null == a || a.size() == 0 || n <= 0 || n > a.size())
            return null;
        String[] b = new String[n];// 辅助空间，保存待输出组合数
        combineToStringList = new ArrayList<String>();
        getCombinationString(a, n, 0, b, 0);
        return combineToStringList;
    }

    private static void getCombinationString(ArrayList a, int n, int begin, String[] b, int index) {
        if (n == 0) {// 如果够n个数了，输出b数组
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < index; i++) {
                buffer.append("").append(b[i]);
            }
            buffer.append("");
            // System.out.println(sbuffer.toString());
            combineToStringList.add(buffer.toString());
            return;
        }
        if (begin + n > a.size()) {
            // System.out.println("return");
            return;
        }
        for (int i = begin; i < a.size(); i++) {
            b[index] = a.get(i).toString();
            // System.out.println("b["+index+"]"+ ":"+b[index]);
            // System.out.println("("+(n-1)+"  "+ (i+1) +"   "+ (index+1) +")");
            getCombinationString(a, n - 1, i + 1, b, index + 1);
        }
    }

    /**
     * 获得一个标准的SimpleDateFormat, yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static SimpleDateFormat getStandardDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 转换文字大小
     *
     * @param context
     * @param id
     * @return
     */
    public static int convertTextSize(Context context, int id) {
        return px2sp(context, context.getResources().getDimensionPixelSize(id));
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String[] listToArray(List<String> list) {
        int len = list.size();
        String[] temp = new String[len];
        for (int i = 0; i < len; i++) {
            temp[i] = list.get(i);
        }
        return temp;
    }

    public static String arrayToStr(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1)
                sb.append(",");
        }
        return sb.toString();
    }


    public static ArrayList<String> getResultListByStr(int lotteryType, String result) {
        ArrayList<String> list = new ArrayList<String>();

        if (result.contains(ALG.SPACE_LINE)) {
            String[] split = result.split(ALG.SPACE_LINE);
            for (String string : split) {
                if (string.contains(ALG.SPLIT_DOT)) {
                    splitDot(list, string);
                } else {
                    list.add(string);
                }
            }
        } else if (result.contains(ALG.SPLIT_DOT)) {
            splitDot(list, result);
        } else {
            char[] charArray = result.toCharArray();
            for (char c : charArray) {
                list.add(String.valueOf(c));
            }
        }
        return list;
    }

    public static void splitDot(ArrayList<String> list, String str) {
        String[] split = str.split(ALG.SPLIT_DOT);
        for (String string : split) {
            list.add(string);
        }
    }

    public static void splitLine(ArrayList<String> list, String str) {
        String[] split = str.split(ALG.SPACE_LINE);
        for (String string : split) {
            list.add(string);
        }
    }

    public static String keepAfterTwoNumber(String number) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(Double.valueOf(number));
    }



    public static void closeActivityByName(final String packgeName, final String actName) {
        LocalActivityMgr am = LocalActivityMgr.getInstance();
        Activity activity = null;
        String activityFullName = "";

        activityFullName = packgeName + "." + actName;
        activity = am.findActivity(activityFullName);
        if (activity != null) {
            activity.finish();
        }
    }




}
