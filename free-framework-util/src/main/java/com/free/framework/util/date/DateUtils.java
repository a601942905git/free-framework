package com.free.framework.util.date;

import com.free.framework.util.date.constant.FormatterPatternConstants;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2017/6/12.
 */
public class DateUtils {

    /**
     * @author lipeng
     * @description         获取当前系统时间,格式为yyyy-MM-dd
     * @param formatPattern 时间格式化方式:yyyy-MM-dd
     * @return              格式化后的当前系统时间
     * @dateTime 2017/8/6 9:33
     */
    public static String getCurrentDate(String formatPattern) {
        String date;
        LocalDate localDate = LocalDate.now();
        formatPattern = StringUtils.isNotEmpty(formatPattern)
                ? formatPattern
                : FormatterPatternConstants.DATE_FORMAT_PATTERN;
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatPattern);
        date = dateTimeFormatter.format(localDate);
        return date;
    }


    /**
     * @author lipeng
     * @description             获取当前系统时间,格式为yyyy-MM-dd HH:mm:ss
     * @param formatPattern     时间格式化方式:yyyy-MM-dd HH:mm:ss
     * @return                  格式化后的当前系统时间
     * @dateTime 2017/8/6 9:34
     */
    public static String getCurrentDateTime(String formatPattern) {
        String dateTime;
        LocalDateTime localDate = LocalDateTime.now();
        formatPattern = StringUtils.isNotEmpty(formatPattern)
                ? formatPattern : FormatterPatternConstants.DATE_TIME_FORMAT_PATTERN;
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatPattern);
        dateTime = dateTimeFormatter.format(localDate);
        return dateTime;
    }


    /**
     * @author lipeng
     * @description             获取日期格式化对象
     * @param formatPattern     时间格式化方式,如yyyy-MM-dd
     * @return                  DateTimeFormatter对象
     * @dateTime 2017/8/6 9:35
     */
    public static DateTimeFormatter getDateTimeFormatter(String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return dateTimeFormatter;
    }

    /**
     * @author lipeng
     * @description             LocalDateTime转Date
     * @param localDateTime     LocalDateTime对象
     * @return                  Date对象
     * @dateTime 2017/8/6 9:36
     */
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        Date date;
        ZoneId zoneId = getSystemZoneId();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        date = Date.from(instant);
        return date;
    }


    /**
     * @author lipeng
     * @description     LocalDateTime转Date
     * @param date      Date对象
     * @return          LocalDateTime对象
     * @dateTime 2017/8/6 9:37
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        ZoneId zoneId = getSystemZoneId();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }

    /**
     * @author lipeng
     * @description         LocalDate转Date
     * @param localDate     LocalDate对象
     * @return              Date对象
     * @dateTime 2017/8/6 9:37
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        Date date;
        ZoneId zoneId = getSystemZoneId();
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        date = Date.from(instant);
        return date;
    }

    /**
     * @author lipeng
     * @description         Date转LocalDate
     * @param date          Date对象
     * @return              LocalDate对象
     * @dateTime 2017/8/6 9:37
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        ZoneId zoneId = getSystemZoneId();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * @author lipeng
     * @description     获取默认时间区
     * @param
     * @return          ZoneId对象
     * @dateTime 2017/8/6 9:39
     */
    public static ZoneId getSystemZoneId() {
        ZoneId zoneId = ZoneId.systemDefault();
        return zoneId;
    }

    /**
     * @author lipeng
     * @description     获取系统默认时间戳
     * @param
     * @return
     * @dateTime 2017/8/6 9:39
     */
    public static Long getSystemMillis() {
        Clock clock = Clock.systemDefaultZone();
        Long systemMillis = clock.millis();
        return systemMillis;
    }

    /**
     * 获取当前系统时间
     * @return  返回当前系统时间
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}
