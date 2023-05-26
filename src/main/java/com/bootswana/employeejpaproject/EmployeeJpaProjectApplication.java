package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.logging.LogSetup;
import com.bootswana.employeejpaproject.services.DepartmentsService;
import com.bootswana.employeejpaproject.services.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EmployeeJpaProjectApplication {

    static Logger logger = Logger.getLogger(EmployeeJpaProjectApplication.class.getName());
	@Autowired
	DepartmentsService departmentsService;

	@Autowired
	SalariesService salariesService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeJpaProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		LogSetup.setup();

		departmentsService.createDepartmentSummary(1986,1987);//5
		salariesService.findAverageSalary("Engineer",1986,1987);//8
		return args -> logger.log(Level.INFO, "All methods have run");
	}
}
