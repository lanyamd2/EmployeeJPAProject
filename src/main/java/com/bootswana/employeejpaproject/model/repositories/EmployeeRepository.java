package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer> {
    // SAMPLE QUERY
    //@Query(value = "SELECT e.* FROM employees.employees e INNER JOIN employees.titles t ON e.emp_no = t.emp_no WHERE e.last_name = :lastName AND t.title = :employeeTitle", nativeQuery = true)
    //List<EmployeeDTO> findSQL(String lastName, String employeeTitle);
}