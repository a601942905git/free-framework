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
     * @return              相加后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = bigDecimal1.add(bigDecimal2).divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         减法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相减后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = bigDecimal1.subtract(bigDecimal2).divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         乘法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相乘后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal multiply(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = bigDecimal1.multiply(bigDecimal2).divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
        return defaultBigDecimal;
    }

    /**
     * @author lipeng
     * @description         除法
     * @param bigDecimal1   金额1
     * @param bigDecimal2   金额2
     * @param scale         保留小数点位数
     * @return              相除后的结果
     * @dateTime 2017/8/6 10:20
     */
    public static BigDecimal divide(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        commonRequireNonNull(bigDecimal1, bigDecimal2);
        BigDecimal defaultBigDecimal;
        defaultBigDecimal = bigDecimal1.divide(bigDecimal2).divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
        return defaultBigDecimal;
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
