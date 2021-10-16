package com.example.studentsystem.controller;

import com.example.studentsystem.model.Student;
import com.example.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public String add(@RequestBody Student student) {
        studentService.save(student);
        return "New student is added ";
    }

    @GetMapping("/students")
    public  List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student get(@PathVariable Integer id) {
        return studentService.get(id);
    }

    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable int id) {
        try{
            studentService.delete(id);
            return "Delete Completion";

        }catch (EmptyResultDataAccessException e){
            return "존재하지 않는 ID 값입니다.";
        }
    }
}
