package com.school_managemtent.service;

import com.school_managemtent.entity.relation.StudentMark;

public interface StudentMarkService {

    StudentMark linkStudentToMark(Long studentId, Long markId, boolean active);
}
