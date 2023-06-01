package com.bootswana.employeejpaproject.model.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "titles")
public class TitleDTO {
    @EmbeddedId
    private TitleDTOId id;

    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "emp_no", nullable = false)
    @JsonBackReference
    private EmployeeDTO empNo;

    @Column(name = "to_date")
    private LocalDate toDate;

    public TitleDTOId getId() {
        return id;
    }

    public void setId(TitleDTOId id) {
        this.id = id;
    }

    public EmployeeDTO getEmpNo() {
        return empNo;
    }

    public void setEmpNo(EmployeeDTO empNo) {
        this.empNo = empNo;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}