package com.example.studentsystem.service;

import com.example.studentsystem.model.Student;

import java.util.List;

public interface StudentService {
    public Student save(Student student);

    public List<Student> getAllStudents();

    public void delete(int id);

    public Student get(Integer id);

}
