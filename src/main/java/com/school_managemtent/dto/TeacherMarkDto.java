package com.school_managemtent.dto;

public class TeacherMarkDto {

    private Long teacherId;
    private Long markId;
    private boolean active;

    public TeacherMarkDto() {
    }

    public TeacherMarkDto(Long teacherId, Long markId) {
        this.teacherId = teacherId;
        this.markId = markId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
