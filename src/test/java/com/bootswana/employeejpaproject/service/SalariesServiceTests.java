package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class SalariesServiceTests {

    @Autowired
    SalariesService salariesService;

    @Autowired
    private SalaryRepository salaryRepository;
    public static Stream<Arguments> checkForNoEmployeesReturnedForTitleAndTimePeriod() {
        return Stream.of(
                Arguments.arguments("Engineer", 1952, 1960),
                Arguments.arguments("Teacher", 1990, 1997),
                Arguments.arguments("Lawyer", 1970, 1980)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that the accurate average salary is returned for the given title over a specific period is not present")
    void checkForNoEmployeesReturnedForTitleAndTimePeriod(String title, int startYear, int endYear) {
        Assertions.assertEquals(0, salariesService.findAverageSalary(title, startYear, endYear));
    }

    public static Stream<Arguments> checkForAverageSalaryPerTitleWithinPeriod() {
        return Stream.of(
                Arguments.arguments("Engineer", 1986, 1987, 49865.07),
                Arguments.arguments("Assistant Engineer", 1990, 1997, 56901.47),
                Arguments.arguments("Technique Leader", 1996, 2002, 62046.95)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that the accurate average salary is returned for the given title over a specific period ")
    void checkForAverageSalaryPerTitleWithinPeriod(String title, int startYear, int endYear, double average) {
        Assertions.assertEquals(average, salariesService.findAverageSalary(title, startYear, endYear));
    }

    public static Stream<Arguments> checkThatNumbersAreAveraged() {
        return Stream.of(
                Arguments.arguments(2.74, Arrays.asList(1.38, 2.56, 4.3)),
                Arguments.arguments(2683.17, Arrays.asList(45.9223, 1823.983, 8783.9892, 78.8)),
                Arguments.arguments(75296.8, Arrays.asList(70283.0, 61948.0, 97482.0, 87382.0, 59389.0))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that method calculated averages accurately with Lists of various lengths")
    void checkThatNumbersAreAveraged(double expected, List<Double> numbers) {
        Assertions.assertEquals(expected, salariesService.average(numbers));
    }

    public static Stream<Arguments> checkThatNumbersRoundDown() {
        return Stream.of(
                Arguments.arguments(9892.98, 9892.98920),
                Arguments.arguments(12.07, 12.07673),
                Arguments.arguments(117.85, 117.85298)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that the number returned is rounded down to the nearest tenth ")
    void checkThatNumbersRoundDown(double expected, double value) {
        Assertions.assertEquals(expected, salariesService.round(value));
    }

    public static Stream<Arguments> checkForTotalEmployeesFoundPerTitleWithinPeriod() {
        return Stream.of(
                Arguments.arguments("Engineer", LocalDate.of(1986, 1, 1), LocalDate.of(1987, 12, 31), 15221),
                Arguments.arguments("Assistant Engineer", LocalDate.of(1990, 1, 1), LocalDate.of(1997, 12, 31), 26456),
                Arguments.arguments("Technique Leader", LocalDate.of(1996, 1, 1), LocalDate.of(2002, 12, 31), 31600)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Check that the accurate number of employees that work in a given title within a specific period is returned")
    void checkForTotalEmployeesFoundPerTitleWithinPeriod(String title, LocalDate startDate, LocalDate endDate, int count) {
        Assertions.assertEquals(count, salaryRepository.findByTitleFromDateEndDate(title, startDate, endDate).size());
    }

    //TEST FOR DEPARTMENT SERVICE METHODS
    public static Stream<Arguments> checkForNoEmployeesInGivenPeriod() {
        return Stream.of(
                Arguments.arguments(1954, 1960),
                Arguments.arguments(1930, 1970),
                Arguments.arguments(1970, 1982)
        );
    }


}
