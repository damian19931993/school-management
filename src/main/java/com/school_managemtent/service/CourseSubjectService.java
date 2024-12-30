package com.school_managemtent.service;

import com.school_managemtent.entity.relation.CourseSubject;

public interface CourseSubjectService {

    CourseSubject linkCourseToSubject(Long courseId, Long subjectId, boolean active);
}
