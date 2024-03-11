package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {

    Optional<Student> findById(int id);

    Student save(Student student);

    void deleteById(int id);

    Student findByDni(String dni);

    List<Student> findAllActiveStudents();

    List<Student> findStudentsByCourseId(Integer courseId);


    Student findById2(int studentId);

    List<Student> findActiveStudentsByCourse(Course course);

}
