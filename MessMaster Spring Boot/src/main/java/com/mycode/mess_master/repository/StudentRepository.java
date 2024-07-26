package com.mycode.mess_master.repository;

import com.mycode.mess_master.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
