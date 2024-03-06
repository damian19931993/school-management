package com.schoolmanagement.com.schoolmanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school_subject")
public class SchoolSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "schoolSubject")
    private Set<TeacherSubjects> teacherSubjects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public SchoolSubject() {
    }

    public SchoolSubject(String subjectName, boolean isActive, Set<TeacherSubjects> teacherSubjects) {
        this.subjectName = subjectName;
        this.isActive = isActive;
        this.teacherSubjects = teacherSubjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<TeacherSubjects> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(Set<TeacherSubjects> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
