package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Mark;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.helper.CourseMarkKey;
import com.school_managemtent.helper.CoursePreceptorKey;
import jakarta.persistence.*;

@Entity
@Table(name = "course_mark")
public class CourseMark {

    @EmbeddedId
    private CourseMarkKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("markId")
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @Column(name = "active")
    private boolean active;

    public CourseMark() {
    }

    public CourseMark(Course course, Mark mark, boolean active) {
        this.course = course;
        this.mark = mark;
        this.active = active;
        this.id = new CourseMarkKey(course.getId(), mark.getId());
    }

    public CourseMarkKey getId() {
        return id;
    }

    public void setId(CourseMarkKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
