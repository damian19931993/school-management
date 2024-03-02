package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Student;
import com.schoolmanagement.com.schoolmanagement.entity.StudentDetail;

import java.util.Optional;

public interface StudentDetailService {
    Optional<StudentDetail> findById(int id);

    StudentDetail save(StudentDetail studentDetail);

    void deleteById(int id);

    StudentDetail findByStudentId(int id);
}
