package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiKeyController {
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyController(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }
}
