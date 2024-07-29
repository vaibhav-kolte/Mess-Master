package com.mycode.mess_master.repository;

import com.mycode.mess_master.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
