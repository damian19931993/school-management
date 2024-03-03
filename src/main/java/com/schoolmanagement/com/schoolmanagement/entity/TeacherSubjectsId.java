package com.schoolmanagement.com.schoolmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class TeacherSubjectsId implements Serializable {


    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "subject_id")
    private int subjectId;

    public TeacherSubjectsId() {
    }

    public TeacherSubjectsId(int teacherId, int subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    // Getters and Setters

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    // hashCode and equals methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherSubjectsId that = (TeacherSubjectsId) o;

        if (teacherId != that.teacherId) return false;
        return subjectId == that.subjectId;
    }

    @Override
    public int hashCode() {
        int result = teacherId;
        result = 31 * result + subjectId;
        return result;
    }
}
