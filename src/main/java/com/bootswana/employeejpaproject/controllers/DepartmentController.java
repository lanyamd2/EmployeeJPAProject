package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DepartmentController {
    private DepartmentRepository departmentRepository;
    private DepartmentsService departmentsService;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, DepartmentsService departmentsService) {
        this.departmentRepository = departmentRepository;
        this.departmentsService = departmentsService;
    }



//    @GetMapping("/departments")
//    public HashMap<String, Integer> getDepartmentCount(@RequestParam int startYear, @RequestParam int endYear){
//      return departmentsService.createDepartmentSummary(startYear, endYear);
//    }
// MethodArgumentTypeMismatchException
    @GetMapping("/departments")
    public ResponseEntity<?> getDepartmentCount(@RequestParam int from, @RequestParam int to){
        //check exception if incorrect datatype is entered on client side - MethodArgumentTypeMismatchException? and
        // log invalid datatype

        //if from>to return years need to be in order ResponseEntity and log invalid request

        HashMap<String,Integer> result = departmentsService.createDepartmentSummary(from, to);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
