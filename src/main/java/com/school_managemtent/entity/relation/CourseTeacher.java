package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.CourseTeacherKey;
import jakarta.persistence.*;

@Entity
@Table(name = "course_teacher")
public class CourseTeacher {

    @EmbeddedId
    private CourseTeacherKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "active")
    private boolean active;

    public CourseTeacher() {
    }

    public CourseTeacher(Course course, Teacher teacher, boolean active) {
        this.course = course;
        this.teacher = teacher;
        this.active = active;
        this.id = new CourseTeacherKey(course.getId(), teacher.getId());
    }

    public CourseTeacherKey getId() {
        return id;
    }

    public void setId(CourseTeacherKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
