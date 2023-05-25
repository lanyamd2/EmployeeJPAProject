package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DepartmentsService {
    static Logger logger = Logger.getLogger(DepartmentsService.class.getName());

    private final DepartmentRepository departmentRepository;

    public DepartmentsService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public void createDepartmentSummary(){
        LocalDate startYear=startYearToLocalDate(getStartYear());
        LocalDate endYear=endYearToLocalDate(getEndYear());
        logger.log(Level.INFO,"Time Period selected from: "+startYear+" to "+endYear);


    }
    public LocalDate startYearToLocalDate(int startYear){
        return LocalDate.of(startYear,1,1);
    }
    public LocalDate endYearToLocalDate(int endYear){
        return LocalDate.of(endYear,12,31);
    }
    public int getStartYear(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the year you like to start the period");
        return scanner.nextInt();
    }
    public int getEndYear(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the year you like to end the period");
        return scanner.nextInt();
    }

}
