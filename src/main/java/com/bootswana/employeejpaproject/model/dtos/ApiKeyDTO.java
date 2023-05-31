package com.bootswana.employeejpaproject.model.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "api_keys", schema = "employees")
public class ApiKeyDTO {
    @Id
    @Size(max = 90)
    @Column(name = "api_key", nullable = false, length = 90)
    private String apiKey;

    @Column(name = "access_level")
    private Integer accessLevel;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "ApiKeyDTO{" +
                "apiKey='" + apiKey + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}