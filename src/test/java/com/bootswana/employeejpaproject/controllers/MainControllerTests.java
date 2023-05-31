package com.bootswana.employeejpaproject.controllers;

import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainControllerTests {
    @Autowired
    EmployeesService employeesService;

    @Autowired
    DepartmentsService departmentsService;

    @Autowired
    SalariesService salariesService;

}
