package com.school_managemtent.service.impl;

import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.StudentSubject;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.StudentSubjectKey;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.repository.StudentSubjectRepository;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentSubjectServiceImpl(StudentSubjectRepository studentSubjectRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public StudentSubject linkStudentToSubject(Long studentId, Long subjectId, boolean active) {
        var key = new StudentSubjectKey(studentId, subjectId);
        Optional<StudentSubject> existingRelation = studentSubjectRepository.findById(key);;

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between student ID " + studentId + " and subject ID " + subjectId + " already exists.");
        } else {
            var student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
            var subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

            var ss = new StudentSubject(student, subject, active);
            studentSubjectRepository.save(ss);

            student.getStudentSubjects().add(ss);
            subject.getStudentSubjects().add(ss);

            return ss;
        }
    }
}
