package com.schoolmanagement.com.schoolmanagement.service;


import com.schoolmanagement.com.schoolmanagement.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course findById(int id);

    void save(Course course);

    void deleteById(int id);

    List<Course> findAllActiveCourses();


    List<Course> findAllByIds(List<Integer> courseIds);
}
