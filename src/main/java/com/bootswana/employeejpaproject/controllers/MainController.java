package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.repositories.ApiKeyRepository;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
public class MainController {
    //services
    private EmployeesService employeesService;
    private DepartmentsService departmentsService;
    private SalariesService salariesService;
    private ApiKeyService apiKeyService;

    @Autowired
    public MainController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @GetMapping("/api/generate/{id}")
    public String generateApiKey(@PathVariable Integer id) {
        return apiKeyService.generateApiKey(id);
    }
}
