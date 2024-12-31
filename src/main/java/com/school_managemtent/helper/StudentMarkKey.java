package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentMarkKey {

    private Long studentId;
    private Long markId;

    public StudentMarkKey() {
    }

    public StudentMarkKey(Long studentId, Long markId) {
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
}
