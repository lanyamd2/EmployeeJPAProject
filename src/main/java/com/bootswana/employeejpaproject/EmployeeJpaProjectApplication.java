package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import com.bootswana.employeejpaproject.services.EmployeesService;
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

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EmployeeJpaProjectApplication.class, args);
		menuLoop();
		ctx.close();
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> logger.log(Level.SEVERE, "Test");
	}

	private static void menuLoop() {
		logger.log(Level.INFO, "Loading menu");

		String choice;
		do {
			choice = menuItems();
			switch (choice) {
				case "1" -> logger.log(Level.INFO, "User chose to \"Find employees by last name\".");
				case "2" -> logger.log(Level.INFO, "User chose to \"Find all the employees who worked in a named department on a given date\".");
				case "3" -> logger.log(Level.INFO, "User chose to \"average salary for a named department on a given date\".");
				case "4" -> logger.log(Level.INFO, "User chose to \"range of salary values within a given year\".");
				case "5" -> logger.log(Level.INFO, "User chose to \"summary of the size of each department (number of staff)\".");
				case "6" -> logger.log(Level.INFO, "User chose to \"gender pay gap\".");
				case "7" -> logger.log(Level.INFO, "User chose to \"Unavailable\".");
				case "8" -> logger.log(Level.INFO, "User chose to \"Unavailable\".");
				case "9" -> logger.log(Level.INFO, "User chose to \"Unavailable\".");
				case "0" -> logger.log(Level.INFO, "User chose to \"Quit\"");
				default -> {
					logger.log(Level.INFO, "Invalid option selected.");
					choice = menuItems();
				}
			}
		}
		while (!choice.equals("0"));
		logger.log(Level.INFO, "Application ending.");
	}

	private static String menuItems() {
		Scanner input = new Scanner(System.in);

		System.out.println("""

				Please select one of the below options
				(1) Find employees by last name
				(2) Find all the employees who worked in a named department on a given date
				(3) average salary for a named department on a given date
				(4) range of salary values within a given year
				(5) summary of the size of each department (number of staff)
				(6) gender pay gap
				(7) Unavailable
				(8) Unavailable
				(9) Unavailable
				(0) Quit""");
		System.out.print("Choice: ");
		return input.nextLine();
	}
}
