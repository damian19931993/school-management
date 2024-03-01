package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByDni(String dni);
}
