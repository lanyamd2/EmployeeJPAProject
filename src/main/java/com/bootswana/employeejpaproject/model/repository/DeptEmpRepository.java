package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.DeptEmpDTO;
import com.bootswana.employeejpaproject.model.DeptEmpDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmpDTO, DeptEmpDTOId> {
}