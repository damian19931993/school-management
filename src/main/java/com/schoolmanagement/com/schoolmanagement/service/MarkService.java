package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.MarkRepository;
import com.schoolmanagement.com.schoolmanagement.entity.*;

import java.util.List;
import java.util.Optional;

public interface MarkService {
    void saveMark(Mark mark);
    Optional<Mark> findMarkById(int id);
    List<Mark> findAllMarks();
    void deleteMarkById(int id);
    List<MarkRepository> findActiveMarksByStudentId(int studentId);

    void createOrUpdateMark(Student student, Course course, Teacher teacher, SchoolSubject subject);

    void save(Mark mark);
}
