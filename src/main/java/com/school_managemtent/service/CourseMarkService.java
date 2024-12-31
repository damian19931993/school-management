package com.school_managemtent.service;

import com.school_managemtent.entity.relation.CourseMark;

public interface CourseMarkService {

    CourseMark linkCourseToMark(Long courseId, Long markId, boolean active);
}
