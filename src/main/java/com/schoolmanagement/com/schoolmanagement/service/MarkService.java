package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.MarkRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Mark;

import java.util.List;
import java.util.Optional;

public interface MarkService {
    Mark saveMark(Mark mark);
    Optional<Mark> findMarkById(int id);
    List<Mark> findAllMarks();
    void deleteMarkById(int id);
    List<MarkRepository> findActiveMarksByStudentId(int studentId);
}
