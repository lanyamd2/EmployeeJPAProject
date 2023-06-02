package com.bootswana.employeejpaproject.service;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;
import com.bootswana.employeejpaproject.model.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<List<EmployeeDTO>> getEmployeesByLastName(String lastName) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding employees with the last name " + lastName + "...");
        List<EmployeeDTO> employees = employeeRepository.getEmployeesByLastName(lastName);
        if(employees.size() == 0) {
            logger.log(Level.INFO, "There are no employees with that last name.");
            return Optional.empty();
        } else {
            logger.log(Level.INFO, employees.size() + " Employees Found:");
            for (EmployeeDTO employee : employees) {
                logger.log(Level.INFO, employee.toString());
            }
            return Optional.of(employees);
        }
    }

    public Optional<List<EmployeeDTO>> getEmployeesByDepartmentNameOnDate(String departmentName, LocalDate chosenDate) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding employees that have worked in the " + departmentName + " department on " + Utility.getDateAsString(chosenDate) + "...");
        List<EmployeeDTO> employees = employeeRepository.findEmployeesByDepartmentNameOnDate(departmentName,chosenDate);
        if(employees.size() == 0) {
            logger.log(Level.INFO, "There are no employees that meet the specified criteria.");
            return Optional.empty();
        } else {
            logger.log(Level.INFO, employees.size() + " Employees Found:");
            for (EmployeeDTO employee : employees) {
                logger.log(Level.INFO, employee.toString());
            }
        }
        return Optional.of(employees);
    }

    public Optional<List<IManagerProjection>> getManagersAndDatesByDepartmentChronologically(String departmentName) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding managers and their from and to dates for the " + departmentName + " department in chronological order...");
        List<IManagerProjection> managersAndDates = employeeRepository.findManagersAndDatesByDepartmentNameChronologically(departmentName);
        if(managersAndDates.size() == 0) {
            logger.log(Level.INFO,"There are no managers that meet the specified criteria.");
            return Optional.empty();
        } else {
            for(IManagerProjection managerAndDates : managersAndDates) {
                logger.log(Level.INFO, Utility.getManagerAsString(managerAndDates));
            }
        }
        return Optional.of(managersAndDates);
    }

    public Optional<List<EmployeeDTO>> getManagersByDepartmentChronologically(String departmentName) {
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Finding managers for the " + departmentName + " department in chronological order...");
        List<EmployeeDTO> managers = employeeRepository.findManagersByDepartmentNameChronologically(departmentName);
        if(managers.size() == 0) {
            logger.log(Level.INFO,"There are no managers that meet the specified criteria.");
            return Optional.empty();
        } else {
            for(EmployeeDTO manager : managers) {
                logger.log(Level.INFO, manager.toString());
            }
        }
        return Optional.of(managers);
    }
}
