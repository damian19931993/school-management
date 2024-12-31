package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentSubjectKey {

    private Long studentId;
    private Long subjectId;

    public StudentSubjectKey() {
    }

    public StudentSubjectKey(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
