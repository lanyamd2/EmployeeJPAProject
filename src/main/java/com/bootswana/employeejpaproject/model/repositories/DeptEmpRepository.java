package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.DeptEmpDTO;
import com.bootswana.employeejpaproject.model.dtos.DeptEmpDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmpDTO, DeptEmpDTOId> {
    @Query(value = "SELECT * FROM employees.dept_emp de WHERE de.from_date<=:fromDate OR de.to_date>=:toDate", nativeQuery = true)
    List<DeptEmpDTO> findByFromDateBeforeAndToDateAfterSQL(LocalDate fromDate, LocalDate toDate);
}