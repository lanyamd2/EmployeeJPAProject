package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utility {
    public static LocalDate startYearToLocalDate(int startYear) {
        return LocalDate.of(startYear, 1, 1);
    }
    public static LocalDate endYearToLocalDate(int endYear) {
        return LocalDate.of(endYear, 12, 31);
    }


    public static String getDateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getManagerAsString(IManagerProjection manager) {
        return  "id=" + manager.getEmpNo() +
                ", birthDate=" + manager.getBirthDate() +
                ", firstName='" + manager.getFirstName() + '\'' +
                ", lastName='" + manager.getLastName() + '\'' +
                ", gender='" + manager.getGender() + '\'' +
                ", hireDate=" + manager.getHireDate() + '\'' +
                ", fromDate=" + manager.getFromDate() + '\'' +
                ", toDate=" + manager.getToDate();
    }
}
