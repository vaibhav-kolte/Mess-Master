package com.mycode.mess_master.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(nullable = false)
    private String departmentName;

    @ElementCollection
    @CollectionTable(name = "courses")
    private Set<String> courses;
}
