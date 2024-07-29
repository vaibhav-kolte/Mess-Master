package com.mycode.mess_master.service;

import com.mycode.mess_master.model.Department;
import com.mycode.mess_master.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentTableRepository;

    public Department get(Integer id) {
        return departmentTableRepository.findById(id).get();
    }

    public void save(Department departmentTable) {
        departmentTableRepository.save(departmentTable);
    }

    public List<Department> listAll() {
        return departmentTableRepository.findAll();
    }

    public void delete(Integer id) {
        departmentTableRepository.deleteById(id);
    }

}
