package com.school_managemtent.service.impl;

import com.school_managemtent.entity.relation.TeacherMark;
import com.school_managemtent.helper.TeacherMarkKey;
import com.school_managemtent.repository.MarkRepository;
import com.school_managemtent.repository.TeacherMarkRepository;
import com.school_managemtent.repository.TeacherRepository;
import com.school_managemtent.service.TeacherMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherMarkServiceImpl implements TeacherMarkService {

    private final TeacherMarkRepository teacherMarkRepository;
    private final TeacherRepository teacherRepository;
    private final MarkRepository markRepository;

    @Autowired
    public TeacherMarkServiceImpl(TeacherMarkRepository teacherMarkRepository, TeacherRepository teacherRepository, MarkRepository markRepository) {
        this.teacherMarkRepository = teacherMarkRepository;
        this.teacherRepository = teacherRepository;
        this.markRepository = markRepository;
    }

    @Override
    public TeacherMark linkTeacherToMark(Long teacherId, Long markId, boolean active) {
        var key = new TeacherMarkKey(teacherId, markId);
        Optional<TeacherMark> existingRelation = teacherMarkRepository.findById(key);

        if (existingRelation.isPresent()) {
            throw new RuntimeException("The relationship between teacher ID " + teacherId + " and mark ID " + markId + " already exists.");
        } else {
            var teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
            var mark = markRepository.findById(markId)
                    .orElseThrow(() -> new RuntimeException("Mark not found with ID: " + markId));

            var tm = new TeacherMark(teacher, mark, active);
            teacherMarkRepository.save(tm);

            teacher.getTeacherMarks().add(tm);
            mark.getTeacherMarks().add(tm);

            return tm;
        }
    }
}
