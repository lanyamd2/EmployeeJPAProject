package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer> {
    @Query(value = "SELECT e.* FROM employees.employees e WHERE e.last_name = :lastName", nativeQuery = true)
    List<EmployeeDTO> getEmployeesByLastName(String lastName);

}