package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmployeesService {
    Logger logger = Logger.getLogger(EmployeesService.class.getName());
    private final EmployeeRepository employeeRepository;
    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void logEmployeesByLastName(String lastName) {
        logger.log(Level.INFO, "Finding employees with the last name " + lastName + "...");
        List<EmployeeDTO> employees = employeeRepository.getEmployeesByLastName(lastName);
        if(employees.size() == 0) {
            logger.log(Level.INFO, "There are no employees with that last name.");
        } else {
            logger.log(Level.INFO, "Employees Found:");
            for (EmployeeDTO employee : employees) {
                logger.log(Level.INFO, employee.toString());
            }
        }
    }

    public void logEmployeesByDepartmentNameOnDate(String departmentName, LocalDate chosenDate) {
        logger.log(Level.INFO, "Finding employees that have worked in the " + departmentName + " department on " + UtilityClass.getDateAsString(chosenDate) + "...");
        List<EmployeeDTO> employees = employeeRepository.findEmployeesByDepartmentNameOnDate(departmentName,chosenDate);
        if(employees == null) {
            logger.log(Level.INFO, "There are no employees that meet the specified criteria.");
        } else {
            for (EmployeeDTO employee : employees) {
                logger.log(Level.INFO, employee.toString());
            }
        }
    }

//    public void logManagersByDepartmentChronologically(String departmentName) {
//        logger.log(Level.INFO, "Finding managers that have ");
//    }
}
