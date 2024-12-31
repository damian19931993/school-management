package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class CourseMarkKey {

    private Long courseId;
    private Long markId;

    public CourseMarkKey() {
    }

    public CourseMarkKey(Long courseId, Long markId) {
        this.courseId = courseId;
        this.markId = markId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }
}
