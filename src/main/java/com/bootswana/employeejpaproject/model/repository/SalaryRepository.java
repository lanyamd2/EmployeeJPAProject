package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.SalaryDTO;
import com.bootswana.employeejpaproject.model.SalaryDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<SalaryDTO, SalaryDTOId> {
}