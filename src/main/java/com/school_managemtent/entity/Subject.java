package com.school_managemtent.entity;

import com.school_managemtent.dto.SubjectDto;
import com.school_managemtent.entity.relation.CourseSubject;
import com.school_managemtent.entity.relation.StudentSubject;
import com.school_managemtent.entity.relation.SubjectMark;
import com.school_managemtent.entity.relation.TeacherSubject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String courseName;
    private String division;
    private String year;
    private String shift;
    private boolean active;

    @Column(unique = true)
    private String uniqueId;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherSubject> teacherSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSubject> courseSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentSubject> studentSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectMark> subjectMarks = new ArrayList<>();

    public Subject() {}

    public Subject(String name, String courseName, String division, String year, String shift, boolean active) {
        this.name = name;
        this.courseName = courseName;
        this.division = division;
        this.year = year;
        this.shift = shift;
        this.active = active;
    }

    public Subject(SubjectDto request) {
        this.name = request.getName();
        this.courseName = request.getCourseName();
        this.division = request.getDivision();
        this.year = request.getYear();
        this.shift = request.getShift();
        this.active = request.isActive();
        this.uniqueId = request.getUniqueId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TeacherSubject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public List<CourseSubject> getCourseSubjects() {
        return courseSubjects;
    }

    public void setCourseSubjects(List<CourseSubject> courseSubjects) {
        this.courseSubjects = courseSubjects;
    }

    public List<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public List<SubjectMark> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(List<SubjectMark> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
