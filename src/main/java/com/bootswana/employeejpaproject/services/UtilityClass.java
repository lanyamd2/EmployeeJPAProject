package com.bootswana.employeejpaproject.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilityClass {
    public static String getDateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
