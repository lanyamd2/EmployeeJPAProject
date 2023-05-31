package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
public class MainController {
    //services
    private EmployeesService employeesService;
    private DepartmentsService departmentsService;
    private SalariesService salariesService;
}
