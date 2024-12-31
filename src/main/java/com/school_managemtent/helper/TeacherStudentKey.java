package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherStudentKey {

    private Long teacherId;
    private Long studentId;

    public TeacherStudentKey() {
    }

    public TeacherStudentKey(Long teacherId, Long studentId) {
        this.teacherId = teacherId;
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
