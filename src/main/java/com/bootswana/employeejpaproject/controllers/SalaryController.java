package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.exception.ApiKeyNotFoundException;
import com.bootswana.employeejpaproject.service.ApiKeyService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
    private ApiKeyService apiKeyService;
    private SalariesService salariesService;

    public SalaryController(ApiKeyService apiKeyService, SalariesService salariesService) {
        this.apiKeyService = apiKeyService;
        this.salariesService = salariesService;
    }

    @GetMapping("/salary/title/average")
    public ResponseEntity<?> getAverageSalaryByTitleAndYearRange(@RequestParam String title, @RequestParam int from, @RequestParam int to, @RequestParam String apiKey) throws ApiKeyNotFoundException {
        apiKeyService.getAccessLevel(apiKey);
        if(from>to){
            return new ResponseEntity<>("Invalid year range, the year " + from + " needs to be after " + to, HttpStatus.BAD_REQUEST);
        }
        double average= salariesService.findAverageSalary(title,from,to);
        if(average==0){
            return new ResponseEntity<>("There are no employees with the title " + title + " between the years " + from+ " and " + to + ".", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The average salary for " + title + " between " + from + " and " + to + " is " + average + ".", HttpStatus.OK);
        }
    }
}
