package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ApiKeyController {
    private ApiKeyRepository apiKeyRepository;
    private ApiKeyService apiKeyService;

    @Autowired
    public ApiKeyController(ApiKeyRepository apiKeyRepository, ApiKeyService apiKeyService) {
        this.apiKeyRepository = apiKeyRepository;
        this.apiKeyService = apiKeyService;
    }

}
