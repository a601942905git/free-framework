package com.free.framework.jdk8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("==========================LocalDate转Date==========================");
        System.out.println(convertLocalDateToDate(localDate));

        System.out.println("==========================Date转LocalDate==========================");
        System.out.println(convertDateToLocalDate(new Date()));
    }

    private static Date convertLocalDateToDate(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        Date date;
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        date = Date.from(instant);
        return date;
    }

    private static LocalDate convertDateToLocalDate(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }
}
