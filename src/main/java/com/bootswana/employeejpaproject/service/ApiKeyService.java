package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.exception.ClientNotAuthorisedException;
import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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

    public int getAccessLevel(String apiKey) throws ApiKeyNotFoundException {
        try {
            Integer level = apiKeyRepository.getApiAccessLevel(apiKey);
            return level;
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "API key not found");
            throw new ApiKeyNotFoundException(apiKey);
        }
    }

    public void checkAccessRights(String apiKey, int accessLevel) throws ApiKeyNotFoundException, ClientNotAuthorisedException {
        int keyLevel = getAccessLevel(apiKey);
        if (keyLevel < accessLevel) {
            String message;
            if (accessLevel == 2) {
                message = "You need access level: 2 (Update User) or 3 (Admin User), you have access level: 1 (Basic User)";
            } else if (keyLevel == 2){
                message = "You need access level: 3 (Admin User), you have access level: 2 (Update User)";
            } else {
                message = "You need access level: 3 (Admin User), you have access level: 1 (Basic User)";
            }
            throw new ClientNotAuthorisedException(message);
        }
    }

    public String generateApiKey(int accessLevel) {
        String key = Utility.generateKey();

        if (!isApiKeyExisting(key)) {
            apiKeyRepository.save(new ApiKeyDTO(key, accessLevel));
            logger.log(Level.WARNING, "-------------------------------------------------------------------------");
            logger.log(Level.WARNING, "New key generated with access level: " + accessLevel);
            return "Access level: " + accessLevel +
                    "<br>Key generated: " + key +
                    "<br><br>Please save this key, as it will not be displayed again.";
        } else {
            logger.log(Level.WARNING, "Error generating key, please try again");
            return "Error generating key, please try again";
        }
    }

    public Boolean isApiKeyExisting(String apiKey) {
        if (apiKeyRepository.getApiKeyDTOByApiKey(apiKey) == (null)) {
            return false;
        } else if (apiKeyRepository.getApiKeyDTOByApiKey(apiKey).equals(apiKey)) {
            return true;
        } else {
            return false;
        }
    }
}
