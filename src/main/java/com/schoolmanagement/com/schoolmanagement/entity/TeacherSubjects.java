package com.schoolmanagement.com.schoolmanagement.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subjects")
public class TeacherSubjects {
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    @MapsId("teacherId")
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @MapsId("subjectId")
    private SchoolSubject schoolSubject;

    @Column(name = "is_active")
    private boolean isActive;

    @EmbeddedId
    private TeacherSubjectsId id;

    public TeacherSubjects() {
    }

    public TeacherSubjects(Teacher teacher, SchoolSubject schoolSubject, boolean isActive) {
        this.teacher = teacher;
        this.schoolSubject = schoolSubject;
        this.isActive = isActive;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(SchoolSubject schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public TeacherSubjectsId getId() {
        return id;
    }

    public void setId(TeacherSubjectsId id) {
        this.id = id;
    }
}
