package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

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

    public static String generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] values = new byte[24];

        secureRandom.nextBytes(values);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(values); //URL and filename safe
    }

    public static String availableEndpoints() {
        String endpoints = "Endpoints available: " +
                System.lineSeparator() +
                "/" + System.lineSeparator() +
                System.lineSeparator() +
                "/api/generate/{accessLevel}" + System.lineSeparator() +
                "/api/check/{apiKey}" + System.lineSeparator() +
                System.lineSeparator() +
                "/employee" + System.lineSeparator() +
                "/employees" + System.lineSeparator() +
                "/employee/create" + System.lineSeparator() +
                "/employee/delete" + System.lineSeparator() +
                "/employees/managers" + System.lineSeparator() +
                "/employee/lastName" + System.lineSeparator() +
                System.lineSeparator() +
                "/department" + System.lineSeparator() +
                "/departments" + System.lineSeparator() +
                "/department/create" + System.lineSeparator() +
                System.lineSeparator() +
                "/salary" + System.lineSeparator() +
                "/salary/average" + System.lineSeparator() +
                "/salary/range" + System.lineSeparator() +
                "/salary/genderPayGap" + System.lineSeparator() +
                "/salary/department/average" + System.lineSeparator() +
                "/salary/progression" + System.lineSeparator();
        return endpoints;
    }
}
