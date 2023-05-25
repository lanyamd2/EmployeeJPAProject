package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class EmployeeJpaProjectApplicationTests {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SalaryRepository salaryRepository;

	@Test
	void contextLoads() {
	}

}
