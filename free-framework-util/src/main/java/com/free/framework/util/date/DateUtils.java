package com.free.framework.util.date;

import com.free.framework.util.date.constant.FormatterPatternConsts;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
