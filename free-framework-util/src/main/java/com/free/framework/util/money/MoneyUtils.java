package com.free.framework.util.money;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * com.free.framework.util.money.MoneyUtils
 *
 * @author lipeng
 * @dateTime 2017/8/6 10:17
 */
public class MoneyUtils {

    /**
     * @author lipeng
     * @description         加法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @param roundingMode  取整模式
     * @return              相加后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale, int roundingMode) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = divideOne(bigDecimal1.add(bigDecimal2), scale, roundingMode);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         加法,取整模式为向上取整
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相加后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        return add(bigDecimal1, bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @author lipeng
     * @description         加法,取整模式为向上取整,默认保留2位有效数字
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @return              相加后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return add(bigDecimal1, bigDecimal2, 2);
    }

    /**
     * @author lipeng
     * @description         减法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @param roundingMode  取整模式
     * @return              相减后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale, int roundingMode) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = divideOne(bigDecimal1.subtract(bigDecimal2), scale, roundingMode);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         减法,取整模式默认向上取整
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相减后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        return subtract(bigDecimal1, bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @author lipeng
     * @description         减法,取整模式默认向上取整,默认保留2位有效数字
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @return              相减后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return subtract(bigDecimal1, bigDecimal2, 2);
    }

    /**
     * @author lipeng
     * @description         乘法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @param roundingMode  取整模式
     * @return              相乘后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal multiply(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale, int roundingMode) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = divideOne(bigDecimal1.multiply(bigDecimal2), scale, roundingMode);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         乘法,取整模式默认向上取整
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相乘后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal multiply(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        return multiply(bigDecimal1, bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @author lipeng
     * @description         乘法,取整模式默认向上取整,默认保留2位有效数字
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @return              相乘后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal multiply(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return multiply(bigDecimal1, bigDecimal2, 2);
    }

    /**
     * @author lipeng
     * @description         除法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @param roundingMode  取整模式
     * @return              相除后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal divide(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale, int roundingMode) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = divideOne(bigDecimal1.divide(bigDecimal2), scale, BigDecimal.ROUND_HALF_UP);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         除法,取整模式默认向上取整
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相除后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal divide(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        return divide(bigDecimal1, bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @author lipeng
     * @description         除法,取整模式默认向上取整,默认保留2位有效数字
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @return              相除后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal divide(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return divide(bigDecimal1, bigDecimal2, 2);
    }

    /**
     * @author lipeng
     * @description         除以1
     * @param bigDecimal    金额
     * @param scale         保留小数点位数
     * @return              相除后的结果
     * @dateTime 2017/8/6 10:20
     */
    private static BigDecimal divideOne(BigDecimal bigDecimal, int scale, int roundingMode) {
        return bigDecimal.divide(BigDecimal.ONE, scale, roundingMode);
    }

    /**
     * @author lipeng
     * @description         判空校验
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @return
     * @dateTime 2017/8/6 10:24
     */
    private static void commonRequireNonNull(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        Objects.requireNonNull(bigDecimal1);
        Objects.requireNonNull(bigDecimal2);
    }
}
