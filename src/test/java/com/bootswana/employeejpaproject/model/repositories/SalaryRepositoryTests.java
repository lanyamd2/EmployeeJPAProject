package com.bootswana.employeejpaproject.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SalaryRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    @DisplayName("Check Get Lowest And Highest Salary For Job Title During A Year")
    void checkGetLowestAndHighestSalaryForJobTitleDuringAYear() {
        String jobTitle = "Engineer";
        int year = 2000;
        Map<String, BigDecimal> salaryMap = salaryRepository.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
        BigDecimal lowestAmountPaid = salaryMap.get("lowest_amount_paid");
        BigDecimal highestAmountPaid = salaryMap.get("highest_amount_paid");
        Assertions.assertTrue(lowestAmountPaid.compareTo(highestAmountPaid) <= 0);
    }

    @Test
    @DisplayName("Check Gender Pay Gap Percentage For Each Job Title Between Two Years")
    void checkGenderPayGapPercentageForEachJobTitleBetweenTwoYearsIsACorrectPercentage() {
        int fromYear = 1990;
        int toYear = 2000;
        List<Object[]> resultRows = salaryRepository.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
        for (Object[] row : resultRows) {
            BigDecimal payGapPercentage = (BigDecimal) row[1];
            Assertions.assertTrue(payGapPercentage.compareTo( new BigDecimal(100)) <= 0);
            Assertions.assertTrue(payGapPercentage.compareTo( new BigDecimal(-100)) >= 0);
        }
    }

    @Test
    void contextLoads() {
    }

}