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

//	SAMPLE TEST
//	@Test
//	@DisplayName("Check Employee")
//	void checkFindByLastName() {
//		String lastName = "Aamodt";
//		String employeeTitle = "Senior Engineer";
//		employeeRepository.findSQL(lastName, employeeTitle).forEach(e -> System.out.println(e.toString()));
//	}

	@Test
	@DisplayName("Check Employee")
	void checkFindByLastName() {
		String jobTitle = "Senior Engineer";
		int year = 2000;
		Map<String, Integer> result = salaryRepository.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);

		for (Map.Entry<String, Integer> entry : result.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + ": " + value);
		}
	}

	@Test
	void contextLoads() {
	}

}
