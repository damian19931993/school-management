package com.school_managemtent.dto;

public class RelativeStudentDto {

    private Long relativeId;
    private Long studentId;
    private boolean active;

    public RelativeStudentDto() {}

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
