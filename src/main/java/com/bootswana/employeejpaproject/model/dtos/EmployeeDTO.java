package com.bootswana.employeejpaproject.model.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class EmployeeDTO {
    @Id
    @NotNull
    @Column(name = "emp_no", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 14)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 14)
    private String firstName;

    @Size(max = 16)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @NotNull
    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "empNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference(value="employee_dept")
    private List<DeptEmpDTO> employeeDepartments;

    @OneToMany(mappedBy = "empNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference(value="manager_dept")
    private List<DeptManagerDTO> managedDepartments;
    @OneToMany(mappedBy = "empNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference(value="salary")
    private List<SalaryDTO> employeeSalaries;

    @OneToMany(mappedBy = "empNo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference(value="title")
    private List<TitleDTO> employeeTitles;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, @NotNull LocalDate birthDate, @NotNull String firstName, @NotNull String lastName, @NotNull String gender, @NotNull LocalDate hireDate) {
        this.id = id;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public List<DeptEmpDTO> getEmployeeDepartments() {
        return employeeDepartments;
    }

    public void setEmployeeDepartments(List<DeptEmpDTO> employeeDepartments) {
        this.employeeDepartments = employeeDepartments;
    }

    public List<DeptManagerDTO> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<DeptManagerDTO> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    public List<SalaryDTO> getEmployeeSalaries() {
        return employeeSalaries;
    }

    public void setEmployeeSalaries(List<SalaryDTO> employeeSalaries) {
        this.employeeSalaries = employeeSalaries;
    }

    public List<TitleDTO> getEmployeeTitles() {
        return employeeTitles;
    }

    public void setEmployeeTitles(List<TitleDTO> employeeTitles) {
        this.employeeTitles = employeeTitles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate;
    }
}