package com.school_managemtent.service;

import com.school_managemtent.entity.relation.StudentSubject;

public interface StudentSubjectService {

    StudentSubject linkStudentToSubject(Long studentId, Long subjectId, boolean active);
}
