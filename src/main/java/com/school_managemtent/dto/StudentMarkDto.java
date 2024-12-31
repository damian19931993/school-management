package com.school_managemtent.dto;

public class StudentMarkDto {

    private Long studentId;
    private Long markId;
    private boolean active;

    public StudentMarkDto() {
    }

    public StudentMarkDto(Long studentId, Long markId) {
        this.studentId = studentId;
        this.markId = markId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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
