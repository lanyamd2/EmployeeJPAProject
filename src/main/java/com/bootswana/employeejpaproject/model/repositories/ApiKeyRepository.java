package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKeyDTO, String> {

    @Query(value = "SELECT k.* FROM employees.api_keys k", nativeQuery = true)
    List<ApiKeyDTO> getAllApiKeys();

    @Query(value = "SELECT k.access_level FROM employees.api_keys k WHERE k.api_key = :apiKey", nativeQuery = true)
    Integer getApiAccessLevel(String apiKey);

    @Query(value = "SELECT k.api_key FROM employees.api_keys k WHERE k.api_key = :apiKey", nativeQuery = true)
    String getApiKeyDTOByApiKey(String apiKey);
}