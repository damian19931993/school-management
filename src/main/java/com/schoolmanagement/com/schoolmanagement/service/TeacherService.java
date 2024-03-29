package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;

import java.security.Principal;
import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();
    Teacher findById(int id);
    void save(Teacher teacher);
    void deleteById(int id);
    List<Teacher> findAllActiveTeachers();
    Teacher findByUsername(String username);

    int findTeacherIdByPrincipal(Principal principal);

    List<Course> findAllActiveCourses(Teacher teacher);

}