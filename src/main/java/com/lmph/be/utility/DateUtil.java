package com.lmph.be.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final String yyyyMMMdd = "yyyy-MMM-dd";
    public static final String DATABASE_FORMAT = "yyyy-mm-dd hh:mm:ss";

    /**
     * Parse given <code>dateString</code> with the help of known Date format.
     *
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param dateString the String to parse
     * @param knownFormat the known format
     * @return {@link LocalDate LocalDate}
     */
    public static LocalDate parseStringKnownFormat(String dateString, String knownFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(knownFormat);
        return LocalDate.parse(dateString, formatter);
    }
}
