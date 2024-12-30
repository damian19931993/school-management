package com.school_managemtent.dto;

public class CoursePreceptorDto {

    private Long courseId;
    private Long preceptorId;
    private boolean active;

    public CoursePreceptorDto() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
