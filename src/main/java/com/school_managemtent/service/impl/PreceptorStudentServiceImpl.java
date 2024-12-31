package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.PreceptorStudent;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.PreceptorStudentKey;
import com.school_managemtent.repository.PreceptorRepository;
import com.school_managemtent.repository.PreceptorStudentRepository;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.service.PreceptorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreceptorStudentServiceImpl implements PreceptorStudentService {

    private final PreceptorStudentRepository preceptorStudentRepository;
    private final PreceptorRepository preceptorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public PreceptorStudentServiceImpl(PreceptorStudentRepository preceptorStudentRepository, PreceptorRepository preceptorRepository, StudentRepository studentRepository) {
        this.preceptorStudentRepository = preceptorStudentRepository;
        this.preceptorRepository = preceptorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public PreceptorStudent linkPreceptorToStudent(Long preceptorId, Long studentId, boolean active) {
        var key = new PreceptorStudentKey(preceptorId, studentId);
        Optional<PreceptorStudent> existingRelation = preceptorStudentRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between preceptor ID " + preceptorId + " and student ID " + studentId + " already exists.");
        } else {
            Preceptor preceptor = preceptorRepository.findById(preceptorId)
                    .orElseThrow(() -> new RuntimeException("Preceptor not found with ID: " + preceptorId));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

            PreceptorStudent ps = new PreceptorStudent(preceptor, student, active);
            preceptorStudentRepository.save(ps);

            preceptor.getPreceptorStudents().add(ps);
            student.getPreceptorStudents().add(ps);

            return ps;
        }
    }
}
