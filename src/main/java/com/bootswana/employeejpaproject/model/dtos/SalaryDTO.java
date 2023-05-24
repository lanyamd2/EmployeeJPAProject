package com.bootswana.employeejpaproject.model.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class SalaryDTO {
    @EmbeddedId
    private SalaryDTOId id;

    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "emp_no", nullable = false)
    private EmployeeDTO empNo;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Integer salary;

    @NotNull
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    public SalaryDTOId getId() {
        return id;
    }

    public void setId(SalaryDTOId id) {
        this.id = id;
    }

    public EmployeeDTO getEmpNo() {
        return empNo;
    }

    public void setEmpNo(EmployeeDTO empNo) {
        this.empNo = empNo;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}