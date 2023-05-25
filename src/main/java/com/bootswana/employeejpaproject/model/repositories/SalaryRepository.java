package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.SalaryDTO;
import com.bootswana.employeejpaproject.model.dtos.SalaryDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryDTO, SalaryDTOId> {

    @Query(value = "SELECT CAST(AVG(s.salary) AS DECIMAL (10,2)) AS average_salary FROM employees.departments d JOIN employees.dept_emp de ON d.dept_no = de.dept_no JOIN employees.salaries s ON de.emp_no = s.emp_no WHERE d.dept_name = :department AND :date BETWEEN s.from_date AND s.to_date;", nativeQuery = true)
    Map<String, BigDecimal> getAverageSalaryForDepartmentOnGivenDate(String department, LocalDate date);

}