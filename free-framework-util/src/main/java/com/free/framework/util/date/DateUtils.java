package com.free.framework.util.date;

import com.free.framework.util.date.constant.FormatterPatternConsts;
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
     * 年月日
     *
     * @param formatPattern
     * @return
     */
    public static String getCurrentDate(String formatPattern) {
        String date;
        LocalDate localDate = LocalDate.now();
        formatPattern = StringUtils.isNotEmpty(formatPattern)
                ? formatPattern
                : FormatterPatternConsts.DATE_FORMAT_PATTERN;
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatPattern);
        date = dateTimeFormatter.format(localDate);
        return date;
    }

    /**
     * 年月日时分秒
     *
     * @param formatPattern
     * @return
     */
    public static String getCurrentDateTime(String formatPattern) {
        String dateTime;
        LocalDateTime localDate = LocalDateTime.now();
        formatPattern = StringUtils.isNotEmpty(formatPattern)
                ? formatPattern : FormatterPatternConsts.DATE_TIME_FORMAT_PATTERN;
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatPattern);
        dateTime = dateTimeFormatter.format(localDate);
        return dateTime;
    }

    /**
     * 日期格式化对象
     *
     * @param formatPattern
     * @return
     */
    public static DateTimeFormatter getDateTimeFormatter(String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return dateTimeFormatter;
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
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
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        ZoneId zoneId = getSystemZoneId();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
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
     * Date转LocalDate
     * @param date
     * @return
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
     * 获取默认时区
     * @return
     */
    public static ZoneId getSystemZoneId() {
        ZoneId zoneId = ZoneId.systemDefault();
        return zoneId;
    }

    /**
     * 获取系统时间戳
     * @return
     */
    public static Long getSystemMillis() {
        Clock clock = Clock.systemDefaultZone();
        Long systemMillis = clock.millis();
        return systemMillis;
    }
}
