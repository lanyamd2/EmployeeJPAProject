package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentDTO, String> {
}