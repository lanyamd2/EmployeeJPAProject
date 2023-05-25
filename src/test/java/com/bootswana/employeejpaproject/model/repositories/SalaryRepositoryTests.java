package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.services.SalariesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SalaryRepositoryTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    void contextLoads() {
    }

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
    @DisplayName("checkAverageSalaryFromDepartmentGivenDate")
    void checkAverageSalaryFromDepartmentGivenDate() {
        BigDecimal expected = BigDecimal.valueOf(68567.95);
        String department = "Finance";
        LocalDate date = LocalDate.of(1995, 6, 26);

        Map<String, BigDecimal> salaryMap = salaryRepository.getAverageSalaryForDepartmentOnGivenDate(department, date);
        BigDecimal averageSalary = salaryMap.get("average_salary");
        Assertions.assertEquals(expected.doubleValue(), averageSalary.doubleValue());
    }

    @Test
    @DisplayName("Check Get First Five Salaries Of An Employee By Employee Number")
    void checkGetFirstFiveSalariesOfAnEmployeeByEmployeeNumber() {
        int empNo = 10001;

        List<Integer> salaryList = salaryRepository.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
        for (Integer salary : salaryList) {
            Assertions.assertTrue(salary >= 0);
        }
    }
}