package com.mycode.mess_master.controller;

import com.mycode.mess_master.model.Department;
import com.mycode.mess_master.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Department departmentTable) {
        departmentService.save(departmentTable);
        return new ResponseEntity<>(departmentTable, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Department> list() {
        return departmentService.listAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        departmentService.delete(id);
    }
}
