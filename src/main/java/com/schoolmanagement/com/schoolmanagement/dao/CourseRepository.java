package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByIsActiveTrue();

    List<Course> findByAssistantIdAndIsActiveTrue(int assistantId);

    @Query("SELECT c FROM Course c JOIN c.teachers t WHERE t.id = :teacherId AND c.isActive = true")
    List<Course> findAllActiveByTeacher(@Param("teacherId") int teacherId);
}
