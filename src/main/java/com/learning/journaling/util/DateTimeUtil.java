package com.learning.journaling.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static LocalDate parseToDate(String date){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, df);
    }

    public static String parseDateToString(LocalDate date){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(df);
    }

    public static String parseDateTimeToString(LocalDateTime date){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return date.format(df);
    }
}
