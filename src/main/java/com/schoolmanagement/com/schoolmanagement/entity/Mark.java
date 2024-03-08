package com.schoolmanagement.com.schoolmanagement.entity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_value")
    private String studentValue;

    @Column(name = "grade_date")
    @Temporal(TemporalType.DATE)
    private Date gradeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SchoolSubject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "is_active")
    private boolean isActive;

    public Mark() {
    }

    public Mark(String studentValue, Date gradeDate, Student student, SchoolSubject subject, Teacher teacher, Course course, boolean isActive) {
        this.studentValue = studentValue;
        this.gradeDate = gradeDate;
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
        this.course = course;
        this.isActive = isActive;
    }
    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentValue() {
        return studentValue;
    }

    public void setStudentValue(String studentValue) {
        this.studentValue = studentValue;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolSubject getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
