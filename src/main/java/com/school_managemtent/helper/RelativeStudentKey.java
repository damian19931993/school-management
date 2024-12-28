package com.school_managemtent.helper;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RelativeStudentKey {

    private Long relativeId;
    private Long studentId;

    public RelativeStudentKey() {}

    public RelativeStudentKey(Long relativeId, Long studentId) {
        this.relativeId = relativeId;
        this.studentId = studentId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelativeStudentKey)) return false;
        RelativeStudentKey that = (RelativeStudentKey) o;
        return Objects.equals(relativeId, that.relativeId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativeId, studentId);
    }
}
