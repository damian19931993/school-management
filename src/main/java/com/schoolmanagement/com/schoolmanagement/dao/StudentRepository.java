package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByDni(String dni);
    List<Student> findByIsActive(boolean isActive);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> findByCourseId(@Param("courseId") Integer courseId);

    List<Student> findByCoursesAndIsActive(Course course, boolean isActive);

}
