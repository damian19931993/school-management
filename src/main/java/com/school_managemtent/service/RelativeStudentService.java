package com.school_managemtent.service;

import com.school_managemtent.entity.relation.RelativeStudent;

public interface RelativeStudentService {

    RelativeStudent linkRelativeToStudent(Long relativeId, Long studentId, boolean active);
}
