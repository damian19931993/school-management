package com.school_managemtent.service;

import com.school_managemtent.entity.relation.TeacherSubject;

import java.util.List;

public interface TeacherSubjectService {

    TeacherSubject linkTeacherToSubject(Long teacherId, Long subjectId, boolean active);
    List<Long> findSubjectByTeacherId(Long teacherId);
}
