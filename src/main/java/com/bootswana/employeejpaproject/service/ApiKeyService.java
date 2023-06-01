package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int getAccessLevel(String apiKey) {
        try{
            Integer level = apiKeyRepository.getApiAccessLevel(apiKey);
            return level;
        }catch(NullPointerException e){
            logger.log(Level.WARNING, "API key not found");
            return 0;
        }
    }

    public String generateApiKey(int accessLevel) {
        if (accessLevel == 1 || accessLevel == 2 || accessLevel == 3) {
            String key = Utility.generateKey();

            if (!isApiKeyExisting(key)) {
                apiKeyRepository.save(new ApiKeyDTO(key, accessLevel));
                logger.log(Level.WARNING, "-------------------------------------------------------------------------");
                logger.log(Level.WARNING, "New key generated with access level: " + accessLevel);
                return "Access level: " + accessLevel +
                        "<br>Key generated: " + key +
                        "<br><br>Please save this key, as it will not be displayed again.";
            }
            else {
                logger.log(Level.WARNING, "Error generating key, please try again");
                return "Error generating key, please try again";
            }

        } else {
            logger.log(Level.WARNING, "The client has not entered a correct API access level");
            return "The client has not entered a correct API access level";//throw client access level not found
        }
    }

    public Boolean isApiKeyExisting(String apiKey) {
        if (apiKeyRepository.getApiKeyDTOByApiKey(apiKey) == (null)) {
            return false;
        }
        else if (apiKeyRepository.getApiKeyDTOByApiKey(apiKey).equals(apiKey)) {
           return true;
        }
        else {
            return false;
        }
    }
}
