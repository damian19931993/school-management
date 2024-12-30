package com.school_managemtent.service;

import com.school_managemtent.entity.relation.TeacherSubject;

public interface TeacherSubjectService {

    TeacherSubject linkTeacherToSubject(Long teacherId, Long subjectId, boolean active);
}
