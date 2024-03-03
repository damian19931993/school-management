package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;

import java.util.List;

public interface SchoolSubjectService {
    List<SchoolSubject> findAll();
    SchoolSubject findById(int id);
    void save(SchoolSubject schoolSubject);
    void deleteById(int id);
}