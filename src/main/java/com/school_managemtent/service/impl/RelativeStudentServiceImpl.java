package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Relative;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.relation.RelativeStudent;
import com.school_managemtent.helper.RelativeStudentKey;
import com.school_managemtent.repository.RelativeRepository;
import com.school_managemtent.repository.RelativeStudentRepository;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.service.RelativeStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RelativeStudentServiceImpl implements RelativeStudentService {

    private final RelativeRepository relativeRepository;
    private final StudentRepository studentRepository;
    private final RelativeStudentRepository relativeStudentRepository;

    @Autowired
    public RelativeStudentServiceImpl(RelativeRepository relativeRepository, StudentRepository studentRepository, RelativeStudentRepository relativeStudentRepository) {
        this.relativeRepository = relativeRepository;
        this.studentRepository = studentRepository;
        this.relativeStudentRepository = relativeStudentRepository;
    }

    @Override
    public RelativeStudent linkRelativeToStudent(Long relativeId, Long studentId, boolean active) {
        var key = new RelativeStudentKey(relativeId, studentId);
        Optional<RelativeStudent> existingRelation = relativeStudentRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between relative ID " + relativeId + " and student ID " + studentId + " already exists.");
        } else {
            Relative relative = relativeRepository.findById(relativeId)
                    .orElseThrow(() -> new RuntimeException("Relative not found with ID: " + relativeId));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

            RelativeStudent rs = new RelativeStudent(relative, student, active);
            relativeStudentRepository.save(rs);

            relative.getRelativeStudents().add(rs);
            student.getRelativeStudents().add(rs);

            return rs;
        }
    }
}