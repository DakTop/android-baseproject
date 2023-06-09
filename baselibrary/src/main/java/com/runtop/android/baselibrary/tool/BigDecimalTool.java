package com.runtop.android.baselibrary.tool;

import java.math.BigDecimal;

/**
 * Created by dell on 2018/4/4.
 */

public class BigDecimalTool {

    /**
     * 保留三位小数(四舍五入)
     *
     * @param number
     * @return double
     */
    public static double keep3DecimalDouble(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留3位小数(四舍五入)
     *
     * @param number
     * @return String
     */
    public static String keep3Decimal(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(3, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * 保留三位小数点(不四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepThreeDecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(3, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 保留3位小数(不四舍五入)
     *
     * @param number
     * @return String
     */
    public static String keep3DecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(3, BigDecimal.ROUND_HALF_DOWN).toString();
    }


    /**
     * 保留2位小数(四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepTwoDecimal(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 保留2位小数(四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepTwoDecimal(String number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留2位小数(四舍五入)
     *
     * @param number
     * @return String
     */
    public static String keep2Decimal(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留两位小数点(不四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepTwoDecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 保留2位小数(不四舍五入)
     *
     * @param number
     * @return String
     */
    public static String keep2DecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
    }


    /**
     * 保留1位小数点(四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepOneDecimal(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留1位小数点(四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static String keepOneDecimalStr(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留1位小数点(不四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepOneDecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(1, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 保留0位小数点(四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepDecimal(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 保留0位小数点(不四舍五入)
     *
     * @param number 需要处理的数
     * @return double
     */
    public static double keepDecimalWithoutRound(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(0, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }
}
