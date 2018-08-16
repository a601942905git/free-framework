package com.free.framework.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * com.free.framework.util.StringUtils
 * 学习apache common的StringUtils
 * @author lipeng
 * @dateTime 2018/7/13 下午2:28
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2018, 6, 15);
        LocalDate endDate = LocalDate.of(2018, 7, 15);
        System.out.println(ChronoUnit.DAYS.between(startDate, endDate));
    }
}
