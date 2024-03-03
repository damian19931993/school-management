package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjects;

import java.util.List;
import java.util.Optional;

public interface TeacherSubjectsService {
    List<TeacherSubjects> findAll();
    Optional<TeacherSubjects> findById(int id);

    TeacherSubjects findById(int teacherId, int subjectId);

    void save(TeacherSubjects teacherSubjects);
    void deleteById(int id);

    void deleteAssignment(int teacherId, int subjectId);

    List<TeacherSubjects> findAssignmentsByTeacherId(int teacherId);


}