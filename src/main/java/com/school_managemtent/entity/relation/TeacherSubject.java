package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.entity.Teacher;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.TeacherSubjectKey;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subject")
public class TeacherSubject {

    @EmbeddedId
    private TeacherSubjectKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "active")
    private boolean active;

    public TeacherSubject() {
    }

    public TeacherSubject(Teacher teacher, Subject subject, boolean active) {
        this.teacher = teacher;
        this.subject = subject;
        this.active = active;
        this.id = new TeacherSubjectKey(teacher.getId(), subject.getId());
    }

    public TeacherSubjectKey getId() {
        return id;
    }

    public void setId(TeacherSubjectKey id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
