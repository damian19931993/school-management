package com.school_managemtent.service;

import com.school_managemtent.entity.relation.PreceptorStudent;

public interface PreceptorStudentService {

    PreceptorStudent linkPreceptorToStudent(Long preceptorId, Long studentId, boolean active);
}
