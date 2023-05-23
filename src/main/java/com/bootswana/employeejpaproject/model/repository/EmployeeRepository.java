package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer> {
}