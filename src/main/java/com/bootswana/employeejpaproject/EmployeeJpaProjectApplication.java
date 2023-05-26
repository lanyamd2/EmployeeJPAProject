package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.logging.LogSetup;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		return args -> logger.log(Level.INFO, "All methods have run");
	}
}
