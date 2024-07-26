package com.mycode.mess_master.controller;

import com.mycode.mess_master.model.DepartmentTable;
import com.mycode.mess_master.service.DepartmentTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentTableController {
    @Autowired
    private DepartmentTableService departmentTableService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DepartmentTable departmentTable) {
        departmentTableService.save(departmentTable);
        return new ResponseEntity<>(departmentTable, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<DepartmentTable> list() {
        return departmentTableService.listAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        departmentTableService.delete(id);
    }
}
