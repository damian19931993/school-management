package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class CoursePreceptorKey {

    private Long courseId;
    private Long preceptorId;

    public CoursePreceptorKey() {
    }

    public CoursePreceptorKey(Long courseId, Long preceptorId) {
        this.courseId = courseId;
        this.preceptorId = preceptorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPreceptorId() {
        return preceptorId;
    }

    public void setPreceptorId(Long preceptorId) {
        this.preceptorId = preceptorId;
    }
}
