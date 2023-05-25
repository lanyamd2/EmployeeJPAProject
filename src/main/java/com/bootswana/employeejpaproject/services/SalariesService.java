package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.EmployeeJpaProjectApplication;
import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SalariesService {
    static Logger logger = Logger.getLogger(SalariesService.class.getName());
    private final SalaryRepository salaryRepository;

    public SalariesService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public void logLowestAndHighestSalaryForJobTitleDuringAYear(String jobTitle, int year) {
        logger.log(Level.INFO, "Finding the lowest and highest salary in the year " + year + ", for a " + jobTitle + "...");
        Map<String, BigDecimal> salaryMap = salaryRepository.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
        BigDecimal lowestAmountPaid = salaryMap.get("lowest_amount_paid");
        BigDecimal highestAmountPaid = salaryMap.get("highest_amount_paid");
        logger.log(Level.INFO, "Lowest Salary: " + lowestAmountPaid);
        logger.log(Level.INFO, "Highest Salary: " + highestAmountPaid);
    }

    public void logGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(int fromYear, int toYear) {
        logger.log(Level.INFO, "Finding the percentage gender pay gap per department between the years " + fromYear + " and " + toYear + "...");
        List<Object[]> resultRows = salaryRepository.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
        for (Object[] row : resultRows) {
            String departmentName = (String) row[0];
            BigDecimal payGapPercentage = (BigDecimal) row[1];
            int compareValue = payGapPercentage.compareTo( new BigDecimal(0));
            if (compareValue > 0) {
                logger.log(Level.INFO, "Males earned " + payGapPercentage + "% more than females in " + departmentName);
            } else if (compareValue < 0) {
                logger.log(Level.INFO, "Females earned " + payGapPercentage.abs() + "% more than males in " + departmentName);
            } else {
                logger.log(Level.INFO, "Males and females earned the same amount in " + departmentName);
            }
        }
    }

    public void logAverageSalaryForDepartmentOnGivenDate(String department, LocalDate date) {
        logger.log(Level.INFO, "Finding the average salary in " + department + " on " + date + "...");
        Map<String, BigDecimal> salaryMap = salaryRepository.getAverageSalaryForDepartmentOnGivenDate(department, date);
        BigDecimal averageSalary = salaryMap.get("average_salary");
        logger.log(Level.INFO, "Average Salary: " + averageSalary);
    }

    public void logFirstFiveSalariesOfAnEmployeeByEmployeeNumber(int empNo) {
        logger.log(Level.INFO, "Finding the first five salaries of employee with the employee number " + empNo + "...");
        List<Integer> salaryList = salaryRepository.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
        logger.log(Level.INFO, "Salaries:");
        for (Integer salary : salaryList) {
            logger.log(Level.INFO, String.valueOf(salary));
        }
    }
}
