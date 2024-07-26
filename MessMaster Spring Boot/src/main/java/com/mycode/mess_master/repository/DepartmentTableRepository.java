package com.mycode.mess_master.repository;

import com.mycode.mess_master.model.DepartmentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentTableRepository extends JpaRepository<DepartmentTable, Integer> {
}
