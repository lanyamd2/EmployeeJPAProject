package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int getAccessLevel(String clientKey) throws ApiKeyNotFoundException {
        try{
            Integer level = apiKeyRepository.getApiAccessLevel(clientKey);
            return level;
        }catch(NullPointerException e){
            logger.log(Level.WARNING, "API key not found");
            throw new ApiKeyNotFoundException(clientKey);
        }
    }

    public int generateApiKey(int accessLevel) {
        if (accessLevel == 1 || accessLevel == 2 || accessLevel == 3) {
            String key = Utility.generateKey();

            apiKeyRepository.save(new ApiKeyDTO(key, accessLevel));
            logger.log(Level.WARNING, "-------------------------------------------------------------------------");
            logger.log(Level.WARNING, "Access level: " + accessLevel + ", Key generated: " + key);
            logger.log(Level.WARNING, "Please save this key, as it will not be displayed again.");
            logger.log(Level.WARNING, "-------------------------------------------------------------------------");
        } else {
            logger.log(Level.WARNING, "The client has not entered a correct API access level");
            return 0;//throw client access level not found
        }
    }
}
