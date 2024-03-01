package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Student;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(int id);

    Student save(Student student);

    void deleteById(int id);

    Student findByDni(String dni);
}
