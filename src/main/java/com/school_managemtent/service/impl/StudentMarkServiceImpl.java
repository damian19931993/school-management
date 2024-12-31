package com.school_managemtent.service.impl;

import com.school_managemtent.entity.relation.StudentMark;
import com.school_managemtent.entity.relation.StudentSubject;
import com.school_managemtent.helper.StudentMarkKey;
import com.school_managemtent.helper.StudentSubjectKey;
import com.school_managemtent.repository.MarkRepository;
import com.school_managemtent.repository.StudentMarkRepository;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.service.StudentMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentMarkServiceImpl implements StudentMarkService {

    private final StudentMarkRepository studentMarkRepository;
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;

    @Autowired
    public StudentMarkServiceImpl(StudentMarkRepository studentMarkRepository, StudentRepository studentRepository, MarkRepository markRepository) {
        this.studentMarkRepository = studentMarkRepository;
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
    }

    @Override
    public StudentMark linkStudentToMark(Long studentId, Long markId, boolean active) {
        var key = new StudentMarkKey(studentId, markId);
        Optional<StudentMark> existingRelation = studentMarkRepository.findById(key);;

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between student ID " + studentId + " and mark ID " + markId + " already exists.");
        } else {
            var student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
            var mark = markRepository.findById(markId)
                    .orElseThrow(() -> new RuntimeException("Mark not found with ID: " + markId));

            var ss = new StudentMark(student, mark, active);
            studentMarkRepository.save(ss);

            student.getStudentMarks().add(ss);
            mark.getStudentMarks().add(ss);

            return ss;
        }
    }
    }

