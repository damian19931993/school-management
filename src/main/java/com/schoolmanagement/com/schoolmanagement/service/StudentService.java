package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(int id);

    Student save(Student student);

    void deleteById(int id);

    Student findByDni(String dni);

    List<Student> findAllActiveStudents();

    List<Student> findStudentsByCourseId(Integer courseId);


}
