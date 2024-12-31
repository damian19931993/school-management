package com.school_managemtent.service;

import com.school_managemtent.entity.relation.CourseStudent;

public interface CourseStudentService {

    CourseStudent linkCourseToStudent(Long courseId, Long studentId, boolean active);
}
