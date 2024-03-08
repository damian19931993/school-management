package com.schoolmanagement.com.schoolmanagement.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subjects")
public class TeacherSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SchoolSubject schoolSubject;

    @Column(name = "is_active")
    private boolean isActive;


    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
