package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.SalaryDTO;
import com.bootswana.employeejpaproject.model.dtos.SalaryDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryDTO, SalaryDTOId> {
    @Query(value = "SELECT CAST(MIN(s.salary) AS DECIMAL(10, 2)) AS lowest_amount_paid, CAST(MAX(s.salary) AS DECIMAL(10, 2)) AS highest_amount_paid FROM employees.titles t JOIN employees.salaries s ON t.emp_no = s.emp_no WHERE t.title = :jobTitle AND YEAR(s.from_date) = :year GROUP BY t.title", nativeQuery = true)
    Map<String, BigDecimal> getLowestAndHighestSalaryForJobTitleDuringAYear(String jobTitle, int year);

    @Query(value = "SELECT t.title, CAST((SUM(CASE WHEN e.gender = 'M' THEN s.salary ELSE 0 END) - SUM(CASE WHEN e.gender = 'F' THEN s.salary ELSE 0 END)) / SUM(s.salary) * 100 AS DECIMAL(10, 2)) AS male_to_female_pay_gap_percentage FROM employees.employees e JOIN employees.titles t ON e.emp_no = t.emp_no JOIN employees.salaries s ON e.emp_no = s.emp_no WHERE (YEAR(s.from_date) BETWEEN :fromYear AND :toYear OR YEAR(s.to_date) BETWEEN :fromYear AND :toYear OR (YEAR(s.from_date) <= :fromYear AND YEAR(s.to_date) >= :toYear)) GROUP BY t.title ORDER BY male_to_female_pay_gap_percentage DESC", nativeQuery = true)
    List<Object[]> getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(int fromYear, int toYear);

    @Query(value = "SELECT CAST(AVG(s.salary) AS DECIMAL(10, 2)) AS average_salary FROM employees.departments d JOIN employees.dept_emp de ON d.dept_no = de.dept_no JOIN employees.salaries s ON de.emp_no = s.emp_no WHERE d.dept_name = :department AND :date BETWEEN s.from_date AND s.to_date", nativeQuery = true)
    Map<String, BigDecimal> getAverageSalaryForDepartmentOnGivenDate(String department, LocalDate date);

    @Query(value = "SELECT salary FROM employees.salaries WHERE emp_no = :empNo ORDER BY from_date LIMIT 5", nativeQuery = true)
    List<Integer> getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(int empNo);

    @Query(value="SELECT s.salary FROM employees.salaries s JOIN employees.titles t ON s.emp_no = t.emp_no WHERE t.title=:title AND (s.from_date BETWEEN :start AND :end OR s.to_date BETWEEN :start AND :end OR (s.from_date<=:start AND s.to_date>=:end))",nativeQuery = true)
    List<Double> findByTitleFromDateEndDate(String title, LocalDate start, LocalDate end);
}