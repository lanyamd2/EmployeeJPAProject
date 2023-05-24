package com.bootswana.employeejpaproject.model.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentDTO {
    @Id
    @Size(max = 4)
    @Column(name = "dept_no", nullable = false, length = 4)
    private String deptNo;

    @Size(max = 40)
    @NotNull
    @Column(name = "dept_name", nullable = false, length = 40)
    private String deptName;

    @OneToMany(mappedBy = "deptNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<DeptEmpDTO> departmentEmployees;

    @OneToMany(mappedBy = "deptNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<DeptManagerDTO> departmentManagers;

    public List<DeptEmpDTO> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(List<DeptEmpDTO> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }

    public List<DeptManagerDTO> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(List<DeptManagerDTO> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}