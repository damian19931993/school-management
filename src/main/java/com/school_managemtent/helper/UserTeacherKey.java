package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTeacherKey implements Serializable {

    private Long userId;
    private Long teacherId;

    public UserTeacherKey() {}

    public UserTeacherKey(Long userId, Long teacherId) {
        this.userId = userId;
        this.teacherId = teacherId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserTeacherKey that = (UserTeacherKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teacherId);
    }

}
