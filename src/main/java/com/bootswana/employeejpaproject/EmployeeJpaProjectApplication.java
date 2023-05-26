package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.logging.LogSetup;
import com.bootswana.employeejpaproject.services.DepartmentsService;
import com.bootswana.employeejpaproject.services.EmployeesService;
import com.bootswana.employeejpaproject.services.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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
	}

	@Bean
	public CommandLineRunner runner() {
		LogSetup.setup();

		Map<Integer, String[]> dataMap = CSVReader.readCSV();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		//1
		String[] data = dataMap.get(1);
		String lastName = data[1];
		employeesService.logEmployeesByLastName(lastName);

		//2
		data = dataMap.get(2);
		String departmentName = data[1];
		LocalDate chosenDate = LocalDate.parse(data[2], formatter);
		employeesService.logEmployeesByDepartmentNameOnDate(departmentName, chosenDate);

		//3
		data = dataMap.get(3);
		String department = data[1];
		LocalDate date = LocalDate.parse(data[2], formatter);
		salariesService.logAverageSalaryForDepartmentOnGivenDate(department, date);

		//4
		data = dataMap.get(4);
		String jobTitle = data[1];
		int year = Integer.parseInt(data[2]);
		salariesService.logLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);

		//5
		data=dataMap.get(5);
		int fromYear= Integer.parseInt(data[1]);
		int toYear=Integer.parseInt(data[2]);
		departmentsService.createDepartmentSummary(fromYear,toYear);

		//6
		data = dataMap.get(6);
		fromYear = Integer.parseInt(data[1]);
		toYear = Integer.parseInt(data[2]);
		salariesService.logGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);

		//7
		data = dataMap.get(7);
		departmentName = data[1];
		employeesService.logManagersByDepartmentChronologically(departmentName);

		//8
		data=dataMap.get(8);
		jobTitle=data[1];
		fromYear=Integer.parseInt(data[2]);
		toYear=Integer.parseInt(data[3]);
		salariesService.findAverageSalary(jobTitle,fromYear,toYear);

		//9
		data = dataMap.get(9);
		int empNo = Integer.parseInt(data[1]);
		salariesService.logFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);

		return args -> logger.log(Level.INFO, "All methods have run");
	}
}
