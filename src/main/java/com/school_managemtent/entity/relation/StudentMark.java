package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Mark;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.StudentMarkKey;
import jakarta.persistence.*;

@Entity
@Table(name = "student_mark")
public class StudentMark {

    @EmbeddedId
    private StudentMarkKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("markId")
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @Column(name = "active")
    private boolean active;

    public StudentMark() {
    }

    public StudentMark(Student student, Mark mark, boolean active) {
        this.student = student;
        this.mark = mark;
        this.active = active;
        this.id = new StudentMarkKey(student.getId(), mark.getId());
    }

    public StudentMarkKey getId() {
        return id;
    }

    public void setId(StudentMarkKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
