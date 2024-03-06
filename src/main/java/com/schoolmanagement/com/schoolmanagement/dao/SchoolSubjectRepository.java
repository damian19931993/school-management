package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Integer> {
    List<SchoolSubject> findByCourseIdAndIsActive(int courseId, Boolean isActive);
}
