package com.school_managemtent.service.impl;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.entity.relation.CourseTeacher;
import com.school_managemtent.entity.relation.TeacherSubject;
import com.school_managemtent.helper.CourseTeacherKey;
import com.school_managemtent.helper.TeacherSubjectKey;
import com.school_managemtent.repository.SubjectRepository;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.repository.TeacherSubjectRepository;
import com.school_managemtent.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {

    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherSubjectServiceImpl(TeacherSubjectRepository teacherSubjectRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherSubjectRepository = teacherSubjectRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public TeacherSubject linkTeacherToSubject(Long teacherId, Long subjectId, boolean active) {
        var key = new TeacherSubjectKey(teacherId, subjectId);
        Optional<TeacherSubject> existingRelation = teacherSubjectRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between teacher ID " + teacherId + " and subject ID " + subjectRepository + " already exists.");
        } else {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

            TeacherSubject ts = new TeacherSubject(teacher, subject, active);
            teacherSubjectRepository.save(ts);

            teacher.getTeacherSubjects().add(ts);
            subject.getTeacherSubjects().add(ts);

            return ts;
        }
    }

    @Override
    public List<Long> findSubjectByTeacherId(Long teacherId) {
       return teacherSubjectRepository.findSubjectIdsByTeacherId(teacherId);
    }
}
