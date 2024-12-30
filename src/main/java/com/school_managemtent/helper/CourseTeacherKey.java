package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class CourseTeacherKey {

    private Long courseId;
    private Long teacherId;

    public CourseTeacherKey() {
    }

    public CourseTeacherKey(Long courseId, Long teacherId) {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
