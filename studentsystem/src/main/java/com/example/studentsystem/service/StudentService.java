package com.example.studentsystem.service;

import com.example.studentsystem.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);

    public List<Student> getAllStudents();

    public void delete(int id);

}
