package com.mycode.mess_master.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String name;
    private String email;
    private String phoneNumber;
    private Date enrollmentDate;
    private String address;
    private String className;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private DepartmentTable department;
}
