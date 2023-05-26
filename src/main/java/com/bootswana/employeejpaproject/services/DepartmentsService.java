package com.bootswana.employeejpaproject.services;
import com.bootswana.employeejpaproject.model.dtos.DeptEmpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bootswana.employeejpaproject.model.dtos.DepartmentDTO;
import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.repositories.DepartmentRepository;
import com.bootswana.employeejpaproject.model.repositories.DeptEmpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentsService {
    public static Logger logger = Logger.getLogger(DepartmentsService.class.getName());
    private final DepartmentRepository departmentRepository;
    private final DeptEmpRepository deptEmpRepository;

    @Autowired
    public DepartmentsService(DepartmentRepository departmentRepository, DeptEmpRepository deptEmpRepository) {
        this.departmentRepository = departmentRepository;
        this.deptEmpRepository = deptEmpRepository;
    }

    public HashMap<String, Integer> createDepartmentSummary(int startYear, int endYear) {
        logger.log(Level.INFO, "");
        LocalDate startDate = Utility.startYearToLocalDate(startYear);
        LocalDate endDate = Utility.endYearToLocalDate(endYear);
        logger.log(Level.INFO, "Finding the number of employees in each department between " + startDate + " and " + endDate + "...");
        List<DeptEmpDTO> employeesFromStartToEnd = deptEmpRepository.findEmpDeptByFromDateAndToDate(startDate, endDate);
        HashMap<String, Integer> deptMap = new HashMap<>();
        if (!employeesFromStartToEnd.isEmpty()) {
            HashMap<String, Integer> map = countEmployeesPerDepartment(employeesFromStartToEnd);
            map.forEach(
                    (key, value) -> deptMap.put(departmentRepository.findById(key).get().getDeptName(), value)
            );
            logger.log(Level.INFO, deptMap.toString());
        } else {
            String message = "There are no employees in any department between " + startYear + " and " + endYear + ".";
            logger.log(Level.WARNING, message);
            deptMap.put("Employees found between " + startYear + " and " + endYear + ": ", 0);
        }
        return deptMap;
    }

    public HashMap<String, Integer> countEmployeesPerDepartment(List<DeptEmpDTO> employeesFromStartToEnd) {
        HashMap<String, Integer> map = new HashMap<>();
        String key = "";
        for (DeptEmpDTO deptEmpDTO : employeesFromStartToEnd) {
            if (map.containsKey(deptEmpDTO.getDeptNo())) {
                key = deptEmpDTO.getDeptNo();
                map.put(key, map.get(key) + 1);
            } else {
                map.put(deptEmpDTO.getDeptNo(), 1);
            }
        }
        return map;
    }
}
