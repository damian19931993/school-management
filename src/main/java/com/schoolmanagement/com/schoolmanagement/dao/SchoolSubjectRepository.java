package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Integer> {
    List<SchoolSubject> findByCourseIdAndIsActive(int courseId, Boolean isActive);

    List<SchoolSubject> findByIsActiveTrue();

    @Query("SELECT ss FROM SchoolSubject ss JOIN ss.teacherSubjects ts WHERE ts.teacher.id = :teacherId AND ts.course.id = :courseId AND ts.isActive = true")
    List<SchoolSubject> findSubjectsByTeacherAndCourse(@Param("teacherId") int teacherId, @Param("courseId") int courseId);

    @Query("SELECT ss FROM SchoolSubject ss JOIN ss.teacherSubjects ts WHERE ts.teacher = :teacher AND ss.isActive = true")
    List<SchoolSubject> findActiveByTeacher(@Param("teacher") Teacher teacher);

}
