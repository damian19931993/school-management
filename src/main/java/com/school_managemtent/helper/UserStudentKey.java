package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserStudentKey {

    private Long userId;
    private Long studentId;

    public UserStudentKey() {
    }

    public UserStudentKey(Long userId, Long studentId) {
        this.userId = userId;
        this.studentId = studentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
