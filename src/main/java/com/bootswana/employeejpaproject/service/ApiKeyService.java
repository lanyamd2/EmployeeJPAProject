package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ApiKeyService {
    public static Logger logger = Logger.getLogger(ApiKeyService.class.getName());
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public int getAccessLevel(String clientKey) {
        if (clientKey == null) {
            logger.log(Level.WARNING, "The client has not entered an API key");
            return 10;//throw client key not found
        } else {
            Optional<Integer> level = apiKeyRepository.getApiAccessLevel(clientKey);
        }
        return 0;
    }
}
