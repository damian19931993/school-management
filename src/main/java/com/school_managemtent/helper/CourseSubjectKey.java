package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class CourseSubjectKey {

    private Long courseId;
    private Long subjectId;

    public CourseSubjectKey() {
    }

    public CourseSubjectKey(Long courseId, Long subjectId) {
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
