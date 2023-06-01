package com.bootswana.employeejpaproject;

public class DataLogWriter {
    /**	@Bean
    //	public CommandLineRunner runner() {
    //		LogSetup.setup();
    //
    //		System.out.println("---------------------------------------------------");
    //		System.out.println("Loading input data from src/main/resources/data.csv");
    //		System.out.println("---------------------------------------------------");
    //		logger.log(Level.INFO, "Last update: " + LocalDateTime.now());
    //
    //		Map<Integer, String[]> dataMap = CSVReader.readCSV();
    //		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //
    //		//1
    //		String[] data = dataMap.get(1);
    //		String lastName = data[1];
    //		employeesService.getEmployeesByLastName(lastName);
    //
    //		//2
    //		try {
    //			data = dataMap.get(2);
    //			String departmentName = data[1];
    //			LocalDate chosenDate = LocalDate.parse(data[2], formatter);
    //			employeesService.getEmployeesByDepartmentNameOnDate(departmentName, chosenDate);
    //		} catch (DateTimeParseException e) {
    //			logger.log(Level.INFO, "The chosen date: " + data[2] + " in line 2 of the CSV is invalid.");
    //		}
    //
    //		//3
    //		try {
    //			data = dataMap.get(3);
    //			String department = data[1];
    //			LocalDate date = LocalDate.parse(data[2], formatter);
    //			salariesService.getAverageSalaryForDepartmentOnGivenDate(department, date);
    //		} catch (DateTimeParseException e) {
    //			logger.log(Level.INFO, "The chosen date: " + data[2] + " in line 3 of the CSV is invalid.");
    //		}
    //
    //		//4
    //		try {
    //			data = dataMap.get(4);
    //			String jobTitle = data[1];
    //			int year = Integer.parseInt(data[2]);
    //			salariesService.getLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);
    //		} catch (NumberFormatException e) {
    //			logger.log(Level.INFO, "The chosen year: " + data[2] + " in line 4 of the CSV is invalid.");
    //		}
    //
    //		//5
    //		try {
    //			data=dataMap.get(5);
    //			int fromYear= Integer.parseInt(data[1]);
    //			int toYear=Integer.parseInt(data[2]);
    //			departmentsService.createDepartmentSummary(fromYear,toYear);
    //		} catch (NumberFormatException e) {
    //			logger.log(Level.INFO, "A chosen year: " + data[1] + " or " + data[2] + " in line 5 of the CSV is invalid.");
    //		}
    //
    //		//6
    //		try {
    //			data = dataMap.get(6);
    //			int fromYear = Integer.parseInt(data[1]);
    //			int toYear = Integer.parseInt(data[2]);
    //			salariesService.getGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);
    //		} catch (NumberFormatException e) {
    //			logger.log(Level.INFO, "A chosen year: " + data[1] + " or " + data[2] + " in line 6 of the CSV is invalid.");
    //		}
    //
    //		//7
    //		data = dataMap.get(7);
    //		String departmentName = data[1];
    //		employeesService.getManagersByDepartmentChronologically(departmentName);
    //
    //		//8
    //		try {
    //			data=dataMap.get(8);
    //			String jobTitle=data[1];
    //			int fromYear=Integer.parseInt(data[2]);
    //			int toYear=Integer.parseInt(data[3]);
    //			salariesService.findAverageSalary(jobTitle,fromYear,toYear);
    //		} catch (NumberFormatException e) {
    //			logger.log(Level.INFO, "A chosen year: " + data[2] + " or " + data[3] + " in line 8 of the CSV is invalid.");
    //		}
    //
    //		//9
    //		try {
    //			data = dataMap.get(9);
    //			int empNo = Integer.parseInt(data[1]);
    //			salariesService.getFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
    //		} catch (NumberFormatException e) {
    //			logger.log(Level.INFO, "The chosen employee number: " + data[1] + " in line 9 of the CSV is invalid.");
    //		}
    //
    //		LogSetup.close();
    //		return args -> logger.log(Level.INFO, "All methods have run");
    //	}**/
}
