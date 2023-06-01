package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SalariesService {
    static Logger logger = Logger.getLogger(SalariesService.class.getName());
    private final SalaryRepository salaryRepository;

    public SalariesService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public Optional<Map<String, BigDecimal>> getLowestAndHighestSalaryForJobTitleDuringAYear(String jobTitle, int year) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding the lowest and highest salary in the year " + year + ", for a " + jobTitle + "...");
        Map<String, BigDecimal> salaryMap = salaryRepository.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
        if (salaryMap.isEmpty()) {
            logger.log(Level.INFO, "No results found for job title: " + jobTitle + ", year: " + year);
            return Optional.empty();
        } else {
            BigDecimal lowestAmountPaid = salaryMap.get("lowest_amount_paid");
            BigDecimal highestAmountPaid = salaryMap.get("highest_amount_paid");
            logger.log(Level.INFO, "Lowest Salary: " + lowestAmountPaid);
            logger.log(Level.INFO, "Highest Salary: " + highestAmountPaid);
            return Optional.of(salaryMap);
        }
    }

    public Optional<List<Object[]>> getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(int fromYear, int toYear) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding the percentage gender pay gap per department between the years " + fromYear + " and " + toYear + "...");
        List<Object[]> resultRows = salaryRepository.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
        if (resultRows.isEmpty()) {
            logger.log(Level.INFO, "No results found for the percentage gender pay gap between years: " + fromYear + " and " + toYear);
            return Optional.empty();
        } else {
            for (Object[] row : resultRows) {
                String departmentName = (String) row[0];
                BigDecimal payGapPercentage = (BigDecimal) row[1];
                int compareValue = payGapPercentage.compareTo(new BigDecimal(0));
                if (compareValue > 0) {
                    logger.log(Level.INFO, "Males earned " + payGapPercentage + "% more than females in " + departmentName);
                } else if (compareValue < 0) {
                    logger.log(Level.INFO, "Females earned " + payGapPercentage.abs() + "% more than males in " + departmentName);
                } else {
                    logger.log(Level.INFO, "Males and females earned the same amount in " + departmentName);
                }
            }
            return Optional.of(resultRows);
        }
    }

    public Optional<Map<String, BigDecimal>> getAverageSalaryForDepartmentOnGivenDate(String department, LocalDate date) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding the average salary in " + department + " on " + date + "...");
        Map<String, BigDecimal> salaryMap = salaryRepository.getAverageSalaryForDepartmentOnGivenDate(department, date);
        if (salaryMap.isEmpty()) {
            logger.log(Level.INFO, "No results found for department: " + department + ", date: " + date);
            return Optional.empty();
        } else {
            BigDecimal averageSalary = salaryMap.get("average_salary");
            logger.log(Level.INFO, "Average Salary: " + averageSalary);
            return Optional.of(salaryMap);
        }
    }

    public Optional<List<Integer>> getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(int empNo) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding the first five salaries of employee with the employee number " + empNo + "...");
        List<Integer> salaryList = salaryRepository.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
        if (salaryList.isEmpty()) {
            logger.log(Level.INFO, "No results found for employee number: " + empNo);
            return Optional.empty();
        } else {
            logger.log(Level.INFO, "Salaries:");
            for (Integer salary : salaryList) {
                logger.log(Level.INFO, String.valueOf(salary));
            }
            return Optional.of(salaryList);
        }
    }
    public double findAverageSalary(String title, int startYear, int endYear) {
        logger.log(Level.INFO, "");
        LocalDate startDate = Utility.startYearToLocalDate(startYear);
        LocalDate endDate = Utility.endYearToLocalDate(endYear);
        logger.log(Level.INFO, "Finding the average salary of " + title + " between " + startDate + " and " + endDate + "...");
        List<Double> salaries = salaryRepository.findByTitleFromDateEndDate(title, startDate, endDate);
        double averageSalary = 0;
        if (!salaries.isEmpty()) {
            averageSalary = average(salaries);
            logger.log(Level.INFO, "The average salary for " + title + " between " + startYear + " and " + endYear + " is " + averageSalary + ".");
        } else {
            logger.log(Level.WARNING, "There are no employees with the title " + title + " between the years " + startYear + " and " + endYear + ".");
        }
        return averageSalary;
    }

    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    public static double average(List<Double> salaries) {
        double sum = 0;
        for (Double salary : salaries) {
            sum += salary;
        }
        return round(sum / salaries.size());
    }

}
