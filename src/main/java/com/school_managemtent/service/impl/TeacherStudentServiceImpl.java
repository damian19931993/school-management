package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.relation.TeacherStudent;
import com.school_managemtent.helper.TeacherStudentKey;
import com.school_managemtent.repository.StudentRepository;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.repository.TeacherStudentRepository;
import com.school_managemtent.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {

    private  final TeacherStudentRepository teacherStudentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherStudentRepository = teacherStudentRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public TeacherStudent linkTeacherToStudent(Long teacherId, Long studentId, boolean active) {
        var key = new TeacherStudentKey(teacherId, studentId);
        Optional<TeacherStudent> existingRelation = teacherStudentRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between teacher ID " + teacherId + " and student ID " + studentId + " already exists.");
        } else {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

            TeacherStudent ts = new TeacherStudent(teacher, student, active);
            teacherStudentRepository.save(ts);

            teacher.getTeacherStudents().add(ts);
            student.getTeacherStudents().add(ts);

            return ts;
        }
    }
}
