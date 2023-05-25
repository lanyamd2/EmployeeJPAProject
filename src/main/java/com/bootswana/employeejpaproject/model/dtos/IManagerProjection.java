package com.bootswana.employeejpaproject.model.dtos;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public interface IManagerProjection {
    Integer getEmpNo();

    LocalDate getBirthDate();

    String getFirstName();

    String getLastName();

    String getGender();

    LocalDate getHireDate();
    LocalDate getFromDate();
    LocalDate getToDate();

    String toString();
}
