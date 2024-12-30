package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.helper.CoursePreceptorKey;
import jakarta.persistence.*;

@Entity
@Table(name = "course_preceptor")
public class CoursePreceptor {

    @EmbeddedId
    private CoursePreceptorKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("preceptorId")
    @JoinColumn(name = "preceptor_id")
    private Preceptor preceptor;

    @Column(name = "active")
    private boolean active;

    public CoursePreceptor() {}

    public CoursePreceptor(Course course, Preceptor preceptor, boolean active) {
        this.course = course;
        this.preceptor = preceptor;
        this.active = active;
        this.id = new CoursePreceptorKey(course.getId(), preceptor.getId());
    }

    public CoursePreceptorKey getId() {
        return id;
    }

    public void setId(CoursePreceptorKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Preceptor getPreceptor() {
        return preceptor;
    }

    public void setPreceptor(Preceptor preceptor) {
        this.preceptor = preceptor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
