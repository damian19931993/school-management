package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.CourseStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @EmbeddedId
    private CourseStudentKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "active")
    private boolean active;

    public CourseStudent() {
    }

    public CourseStudent(Course course, Student student, boolean active) {
        this.course = course;
        this.student = student;
        this.active = active;
        this.id = new CourseStudentKey(course.getId(), student.getId());
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CourseStudentKey getId() {
        return id;
    }

    public void setId(CourseStudentKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
