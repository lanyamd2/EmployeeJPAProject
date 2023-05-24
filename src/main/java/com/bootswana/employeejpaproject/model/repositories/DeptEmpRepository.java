package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.DeptEmpDTO;
import com.bootswana.employeejpaproject.model.dtos.DeptEmpDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmpDTO, DeptEmpDTOId> {
}