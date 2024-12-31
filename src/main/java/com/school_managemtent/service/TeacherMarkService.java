package com.school_managemtent.service;

import com.school_managemtent.entity.relation.TeacherMark;

public interface TeacherMarkService {

    TeacherMark linkTeacherToMark(Long teacherId, Long markId, boolean active);
}
