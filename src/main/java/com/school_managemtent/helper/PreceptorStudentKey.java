package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class PreceptorStudentKey {

    private Long preceptorId;
    private Long studentId;

    public PreceptorStudentKey() {
    }

    public PreceptorStudentKey(Long preceptorId, Long studentId) {
        this.preceptorId = preceptorId;
        this.studentId = studentId;
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
}
