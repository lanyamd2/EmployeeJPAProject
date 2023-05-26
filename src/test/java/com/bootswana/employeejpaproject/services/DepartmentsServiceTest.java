package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.DeptEmpRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.stream.Stream;

@SpringBootTest
public class DepartmentsServiceTest {
    @Autowired
    DeptEmpRepository deptEmpRepository;

    @Autowired
    DepartmentsService departmentsService;
    public static Stream<Arguments> checkForNoEmployeesInGivenPeriod() {
        return Stream.of(
                Arguments.arguments(1954, 1960),
                Arguments.arguments(1930, 1970),
                Arguments.arguments(1970, 1982)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check for no employees between the given period")
    void checkForNoEmployeesInGivenPeriod(int startYear, int endYear) {
        String expectedString = "{Employees found between " + startYear + " and " + endYear + ": =0}";
        HashMap<String, Integer> map = departmentsService.createDepartmentSummary(startYear, endYear);
        Assertions.assertEquals(expectedString, map.toString());
    }

    public static Stream<Arguments> checkForDepartmentSummary() {
        return Stream.of(
                Arguments.arguments(1986, 1987, 9778, "Sales"),
                Arguments.arguments(1990, 1997, 15742, "Marketing"),
                Arguments.arguments(1996, 2002, 77694, "Development")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that method returns the accurate number of employees between given years in specific departments ")
    void checkForDepartmentSummary(int startYear, int endYear, int count, String department) {
        Assertions.assertEquals(count, departmentsService.createDepartmentSummary(startYear, endYear).get(department));
    }

    public static Stream<Arguments> checkForTotalEmployeesFoundWithinPeriod() {
        return Stream.of(
                Arguments.arguments(LocalDate.of(1986, 1, 1), LocalDate.of(1987, 12, 31), 58792),
                Arguments.arguments(LocalDate.of(1990, 1, 1), LocalDate.of(1997, 12, 31), 269131),
                Arguments.arguments(LocalDate.of(1996, 1, 1), LocalDate.of(2002, 12, 31), 301839)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that method returns the accurate number of employees between given years")
    void checkForTotalEmployeesFoundWithinPeriod(LocalDate start, LocalDate end, int count) {
        Assertions.assertEquals(count, deptEmpRepository.findEmpDeptByFromDateAndToDate(start, end).size());
    }

    public static Stream<Arguments> checkForYearConversionToJanuaryFirst() {
        return Stream.of(
                Arguments.arguments(LocalDate.of(1999, 1, 1), 1999),
                Arguments.arguments(LocalDate.of(1982, 1, 1), 1982),
                Arguments.arguments(LocalDate.of(1965, 1, 1), 1965)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that year as integer is being converted to the LocalDate yyyy-1-1")
    void checkForYearConversionToJanuaryFirst(LocalDate startDate, int year) {
        Assertions.assertEquals(startDate, Utility.startYearToLocalDate(year));
    }

    public static Stream<Arguments> checkForYearConversionToDecemberThirtyFirst() {
        return Stream.of(
                Arguments.arguments(LocalDate.of(1999, 12, 31), 1999),
                Arguments.arguments(LocalDate.of(1982, 12, 31), 1982),
                Arguments.arguments(LocalDate.of(1965, 12, 31), 1965)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that year as integer is being converted to the LocalDate yyyy-1-1")
    void checkForYearConversionToDecemberThirtyFirst(LocalDate endDate, int year) {
        Assertions.assertEquals(endDate, Utility.endYearToLocalDate(year));
    }


}
