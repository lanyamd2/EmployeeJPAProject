package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiKeyRepository extends JpaRepository<ApiKeyDTO, String> {

    @Query(value = "SELECT k.* FROM employees.api_keys k", nativeQuery = true)
    List<ApiKeyDTO> getAllApiKeys();

}