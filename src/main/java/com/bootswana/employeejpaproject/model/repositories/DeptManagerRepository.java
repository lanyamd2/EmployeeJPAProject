package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.DeptManagerDTO;
import com.bootswana.employeejpaproject.model.dtos.DeptManagerDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManagerDTO, DeptManagerDTOId> {
}