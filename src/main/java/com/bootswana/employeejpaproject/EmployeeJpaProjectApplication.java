package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.logging.LogSetup;
import com.bootswana.employeejpaproject.service.DepartmentsService;
import com.bootswana.employeejpaproject.service.EmployeesService;
import com.bootswana.employeejpaproject.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EmployeeJpaProjectApplication {

	@Autowired
	SalariesService salariesService;

	@Autowired
	EmployeesService employeesService;

	@Autowired
	DepartmentsService departmentsService;

    static Logger logger = Logger.getLogger(EmployeeJpaProjectApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(EmployeeJpaProjectApplication.class, args);
//		LogSetup.setup();
//		logger.log(Level.INFO, "Last update: " + LocalDateTime.now());
//		System.out.println("--------------------------------------------------------------");
//		System.out.println("Execution complete, results saved in src/main/logs/logFile.log");
//		System.out.println("--------------------------------------------------------------");
	}
}
