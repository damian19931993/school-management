package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class CourseStudentKey {

    private Long courseId;
    private Long studentId;

    public CourseStudentKey() {
    }

    public CourseStudentKey(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
