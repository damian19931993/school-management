package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findAllByIsActiveTrue();

    @Query("SELECT t FROM Teacher t WHERE t.user.username = :username")
    Optional<Teacher> findByUsername(@Param("username") String username);



}
