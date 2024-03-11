package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;

import java.util.List;

public interface SchoolSubjectService {
    List<SchoolSubject> findAll();
    SchoolSubject findById(int id);
    void save(SchoolSubject schoolSubject);
    void deleteById(int id);

     List<SchoolSubject> findActiveSubjectsByCourse(int courseId);
    List<SchoolSubject> findAllActiveSubjects();

    List<SchoolSubject> findSubjectsByTeacherAndCourse(int teacherId, int courseId);

    List<SchoolSubject> findActiveSubjectsByTeacher(Teacher teacher);

}