package com.school_managemtent.dto;

public class CourseMarkDto {

    private Long courseId;
    private Long markId;
    private boolean active;

    public CourseMarkDto() {
    }

    public CourseMarkDto(Long courseId, Long markId) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
