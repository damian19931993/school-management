package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.TeacherSubjectRepository;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {

    @Autowired
    TeacherSubjectRepository teacherSubjectRepository;

    @Override
    public TeacherSubjects save(TeacherSubjects teacherSubjects) {
        return teacherSubjectRepository.save(teacherSubjects);
    }

}
