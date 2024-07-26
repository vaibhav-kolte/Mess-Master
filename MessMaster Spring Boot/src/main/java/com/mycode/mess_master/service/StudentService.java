package com.mycode.mess_master.service;

import com.mycode.mess_master.model.Student;
import com.mycode.mess_master.repository.DepartmentTableRepository;
import com.mycode.mess_master.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentTableRepository departmenttableRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    public Student updateStudent(Integer studentId, Student student) {
        if (studentRepository.existsById(studentId)) {
            student.setStudentId(studentId);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
