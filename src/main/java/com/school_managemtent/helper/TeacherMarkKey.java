package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherMarkKey {

    private Long teacherId;
    private Long markId;

    public TeacherMarkKey() {
    }

    public TeacherMarkKey(Long teacherId, Long markId) {
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
}
