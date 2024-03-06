package com.schoolmanagement.com.schoolmanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="grade")
    private int grade;
    @Column(name="division")
    private String division;
    @Column(name="orientation")
    private String orientation;
    @Column(name="shift")
    private String shift;

    @ManyToMany(mappedBy = "courses")
    private Set<Assistant> assistant;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;

    @ManyToMany
    @JoinTable(
            name = "course_subject",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<SchoolSubject> subjects;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();


    public Course() {
    }

    public Course(int grade, String division, String orientation, String shift, Boolean isActive, Set<Teacher> teachers, Set<SchoolSubject> subjects, Set<Student> students) {
        this.grade = grade;
        this.division = division;
        this.orientation = orientation;
        this.shift = shift;
        this.isActive = isActive;
        this.teachers = teachers;
        this.subjects = subjects;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Set<Assistant> getAssistant() {
        return assistant;
    }

    public void setAssistant(Set<Assistant> assistant) {
        this.assistant = assistant;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<SchoolSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SchoolSubject> subjects) {
        this.subjects = subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
