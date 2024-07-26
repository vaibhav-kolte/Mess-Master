package com.mycode.mess_master.service;

import com.mycode.mess_master.model.DepartmentTable;
import com.mycode.mess_master.repository.DepartmentTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentTableService {

    @Autowired
    private DepartmentTableRepository departmentTableRepository;

    public DepartmentTable get(Integer id) {
        return departmentTableRepository.findById(id).get();
    }

    public void save(DepartmentTable departmentTable) {
        departmentTableRepository.save(departmentTable);
    }

    public List<DepartmentTable> listAll() {
        return departmentTableRepository.findAll();
    }

    public void delete(Integer id) {
        departmentTableRepository.deleteById(id);
    }

}
