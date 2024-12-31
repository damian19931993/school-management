package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.entity.Subject;
import com.school_managemtent.helper.CoursePreceptorKey;
import com.school_managemtent.helper.StudentSubjectKey;
import jakarta.persistence.*;

@Entity
@Table(name = "student_subject")
public class StudentSubject {

    @EmbeddedId
    private StudentSubjectKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "active")
    private boolean active;

    public StudentSubject() {
    }

    public StudentSubject(Student student, Subject subject, boolean active) {
        this.student = student;
        this.subject = subject;
        this.active = active;
        this.id = new StudentSubjectKey(student.getId(), subject.getId());
    }

    public StudentSubjectKey getId() {
        return id;
    }

    public void setId(StudentSubjectKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
