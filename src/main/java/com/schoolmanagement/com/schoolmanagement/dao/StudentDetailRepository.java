package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentDetailRepository extends JpaRepository<StudentDetail,Integer> {
    Optional<StudentDetail> findByStudentId(int studentId);
}
