package com.school_managemtent.dto;

public class PreceptorStudentDto {

    private Long preceptorId;
    private Long studentId;
    private boolean active;

    public PreceptorStudentDto() {
    }

    public Long getPreceptorId() {
        return preceptorId;
    }

    public void setPreceptorId(Long preceptorId) {
        this.preceptorId = preceptorId;
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
