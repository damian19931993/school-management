package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherSubjectKey {

    private Long teacherId;
    private Long subjectId;

    public TeacherSubjectKey() {
    }

    public TeacherSubjectKey(Long teacherId, Long subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
