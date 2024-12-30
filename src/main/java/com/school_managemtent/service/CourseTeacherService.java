package com.school_managemtent.service;

import com.school_managemtent.entity.relation.CourseTeacher;

public interface CourseTeacherService{

    CourseTeacher linkCourseToTeacher(Long courseId, Long teacherId, boolean active);
}
