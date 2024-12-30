package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.CourseSubjectKey;
import jakarta.persistence.*;

@Entity
@Table(name = "course_subject")
public class CourseSubject {

    @EmbeddedId
    private CourseSubjectKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "active")
    private boolean active;

    public CourseSubject() {
    }

    public CourseSubject(Course course, Subject subject, boolean active) {
        this.course = course;
        this.subject = subject;
        this.active = active;
        this.id = new CourseSubjectKey(course.getId(), subject.getId());
    }

    public CourseSubjectKey getId() {
        return id;
    }

    public void setId(CourseSubjectKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
