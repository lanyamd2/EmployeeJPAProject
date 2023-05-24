package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeJpaProjectApplicationTests {
	@Autowired
	EmployeeRepository employeeRepository;

//	SAMPLE TEST
//	@Test
//	@DisplayName("Check Employee")
//	void checkFindByLastName() {
//		String lastName = "Aamodt";
//		String employeeTitle = "Senior Engineer";
//		employeeRepository.findSQL(lastName, employeeTitle).forEach(e -> System.out.println(e.toString()));
//	}

	@Test
	void contextLoads() {
	}

}
