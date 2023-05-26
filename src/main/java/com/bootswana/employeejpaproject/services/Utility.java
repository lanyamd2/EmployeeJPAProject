package com.bootswana.employeejpaproject.services;

import java.time.LocalDate;

public class Utility {
    public static LocalDate startYearToLocalDate(int startYear) {
        return LocalDate.of(startYear, 1, 1);
    }

    public static LocalDate endYearToLocalDate(int endYear) {
        return LocalDate.of(endYear, 12, 31);
    }
}
