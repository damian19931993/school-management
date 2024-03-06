package com.schoolmanagement.com.schoolmanagement.service;


import com.schoolmanagement.com.schoolmanagement.entity.Assistant;
import com.schoolmanagement.com.schoolmanagement.entity.Course;

import java.util.List;
import java.util.Optional;

public interface AssistantService {
    List<Assistant> findAll();

    Assistant findById(int id);

    void save(Assistant assistant);

    void deleteById(int id);

    void assignCourses(int assistantId, List<Integer> courseIds);

    Optional<Assistant> findByUsername(String username);

    List<Course> findActiveCoursesByAssistantUsername(String username);


}
