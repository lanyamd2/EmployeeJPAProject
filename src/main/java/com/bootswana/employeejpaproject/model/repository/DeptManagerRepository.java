package com.bootswana.employeejpaproject.model.repository;

import com.bootswana.employeejpaproject.model.DeptManagerDTO;
import com.bootswana.employeejpaproject.model.DeptManagerDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptManagerRepository extends JpaRepository<DeptManagerDTO, DeptManagerDTOId> {
}