package com.runtop.android.baselibrary.tool;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateTimeTool {
    private static String time = null;

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    public final static String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据传入的过去的时间戳（精确到毫秒）算出距离当前日期有几天
     *
     * @param time
     * @return
     */
    public static int getPastDays(long time) {
        long diff = (new Date()).getTime() - time;
        if (diff <= day) {
            return 0;
        } else {
            return (int) (diff / day);
        }
    }

    /**
     * 将传入的时间（yyyy-MM-dd HH:mm:ss格式）转换为时间戳
     *
     * @param strDate
     * @return
     */
    public static long toDateLong(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date date = sdf.parse(strDate);
            return date.getTime();
        } catch (Exception E) {
            return System.currentTimeMillis();
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String dateVal) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateVal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 将传入的时间（yyyyMMddHHmmssSSS格式）转换为时间戳
     *
     * @param strDate
     * @return
     */
    public static String switchDate(String strDate) {
        if (TextUtils.isEmpty(strDate)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        try {
            Date date = sdf.parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            return formatter.format(date.getTime());
        } catch (Exception E) {
            return "";
        }
    }

    public static String getSystemDateTimeWithNoYear() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd HH:mm:ss");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前完整时间包括毫秒
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemDateTimeSSS() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMddHHmmssSSS");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前完整时间包括毫秒
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemFormatDateTime(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date(System.currentTimeMillis() + millis);// 获取当前完整时间
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemFormatDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前完整时间
        time = formatter.format(nowTime);
        return time;
    }

    // 获取当前完整时间
    public static String getSystemDateTime() {
        return getSystemDateTime(0);
    }

    public static String getSystemDateTime(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date nowTime = new Date(System.currentTimeMillis() - millis);// 获取以前的完整时间
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemFormatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前年月日
        time = formatter.format(nowTime);
        return time;
    }

    // 获取当前年月日
    public static String getSystemDate() {
        return getSystemDate(0);
    }

    public static String getSystemDate(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMdd");
        Date nowTime = new Date(System.currentTimeMillis() - millis); //获取以前的年月日
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemFormatTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前时分秒
        time = formatter.format(nowTime);
        return time;
    }

    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        Date nowTime = new Date(System.currentTimeMillis());// 获取当前时分秒
        time = formatter.format(nowTime);
        return time;
    }

    public static String getDate(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMdd", Locale.getDefault());
        Date nowTime = new Date(millis);// 获取当前年月日
        time = formatter.format(nowTime);
        return time;
    }

    public static String getDateTime(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date nowTime = new Date(millis);// 获取当前年月日
        time = formatter.format(nowTime);
        return time;
    }

    public static String getTime(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss", Locale.getDefault());
        Date nowTime = new Date(millis);// 获取当前时分秒
        time = formatter.format(nowTime);
        return time;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past 前几天
     */
    public static String getPastDate(int past) {
        return getPastDate(FORMAT_YYYY_MM_DD, past);
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past 前几天
     */
    public static String getPastDate(String formatType, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.CHINA);
        return format.format(today);
    }


}
