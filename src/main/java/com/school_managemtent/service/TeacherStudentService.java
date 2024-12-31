package com.school_managemtent.service;

import com.school_managemtent.entity.relation.TeacherStudent;

public interface TeacherStudentService {

    TeacherStudent linkTeacherToStudent(Long teacherId, Long studentId, boolean active);
}
