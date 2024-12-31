package com.school_managemtent.entity;

import com.school_managemtent.dto.CourseDto;
import com.school_managemtent.entity.relation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String division;
    private String year;
    private String shift;
    private boolean active;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePreceptor> coursePreceptors = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseTeacher> courseTeachers = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSubject> courseSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseMark> courseMarks = new ArrayList<>();


    public Course() {
    }

    public Course(String name, String division, String year, String shift, boolean active) {
        this.name = name;
        this.division = division;
        this.year = year;
        this.shift = shift;
        this.active = active;
    }

    public Course(CourseDto request){
        this.name = request.getName();
        this.division = request.getDivision();
        this.year = request.getYear();
        this.shift = request.getShift();
        this.active = request.isActive();
    }

    public void addPreceptorAssociation(Preceptor preceptor, boolean active) {
        for (CoursePreceptor rs : this.coursePreceptors) {
            if (rs.getPreceptor().getId().equals(preceptor.getId())) {
                rs.setActive(active);
                return;
            }
        }
        CoursePreceptor coursePreceptor = new CoursePreceptor(this, preceptor, active);
        this.coursePreceptors.add(coursePreceptor);
        preceptor.getCoursePreceptors().add(coursePreceptor);
    }

    public void addTeacherAssociation(Teacher teacher, boolean active) {
        for (CourseTeacher rs : this.courseTeachers) {
            if (rs.getTeacher().getId().equals(teacher.getId())) {
                rs.setActive(active);
                return;
            }
        }
        CourseTeacher courseTeacher = new CourseTeacher(this, teacher, active);
        this.courseTeachers.add(courseTeacher);
        teacher.getCourseTeachers().add(courseTeacher);
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

    public List<CoursePreceptor> getCoursePreceptors() {
        return coursePreceptors;
    }

    public void setCoursePreceptors(List<CoursePreceptor> coursePreceptors) {
        this.coursePreceptors = coursePreceptors;
    }

    public List<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(List<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public List<CourseSubject> getCourseSubjects() {
        return courseSubjects;
    }

    public void setCourseSubjects(List<CourseSubject> courseSubjects) {
        this.courseSubjects = courseSubjects;
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public List<CourseMark> getCourseMarks() {
        return courseMarks;
    }

    public void setCourseMarks(List<CourseMark> courseMarks) {
        this.courseMarks = courseMarks;
    }
}
