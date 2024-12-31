package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Mark;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.helper.TeacherMarkKey;
import com.school_managemtent.helper.TeacherStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_mark")
public class TeacherMark {

    @EmbeddedId
    private TeacherMarkKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @MapsId("markId")
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @Column(name = "active")
    private boolean active;

    public TeacherMark() {
    }

    public TeacherMark(Teacher teacher, Mark mark, boolean active) {
        this.teacher = teacher;
        this.mark = mark;
        this.active = active;
        this.id = new TeacherMarkKey(teacher.getId(), mark.getId());
    }

    public TeacherMarkKey getId() {
        return id;
    }

    public void setId(TeacherMarkKey id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
