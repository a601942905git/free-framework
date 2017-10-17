package com.free.framwork.jdk8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Objects;

public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

        System.out.println("======================================================================");
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println("==========================LocalDateTime转Date==========================");
        System.out.println(convertLocalDateTimeToDate(localDateTime));

        System.out.println("==========================Date转LocalDateTime==========================");
        System.out.println(convertDateToLocalDateTime(new Date()));
    }

    private static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        Date date;
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        date = Date.from(instant);
        return date;
    }

    private static LocalDateTime convertDateToLocalDateTime(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }
}
