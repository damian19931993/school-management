package com.school_managemtent.service;

import com.school_managemtent.entity.relation.CoursePreceptor;
import com.school_managemtent.entity.relation.RelativeStudent;

public interface CoursePreceptorService {

    CoursePreceptor linkCourseToPreceptor(Long courseId, Long preceptorId, boolean active);
}
