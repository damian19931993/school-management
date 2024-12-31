package com.school_managemtent.entity.relation;

import com.school_managemtent.entity.Course;
import com.school_managemtent.entity.Preceptor;
import com.school_managemtent.entity.Student;
import com.school_managemtent.helper.PreceptorStudentKey;
import jakarta.persistence.*;

@Entity
@Table(name = "preceptor_student")
public class PreceptorStudent {

    @EmbeddedId
    private PreceptorStudentKey id;

    @ManyToOne
    @MapsId("preceptorId")
    @JoinColumn(name = "preceptor_id")
    private Preceptor preceptor;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "active")
    private boolean active;

    public PreceptorStudent() {
    }

    public PreceptorStudent(Preceptor preceptor, Student student, boolean active) {
        this.preceptor = preceptor;
        this.student = student;
        this.active = active;
        this.id = new PreceptorStudentKey(preceptor.getId(), student.getId());
    }

    public PreceptorStudentKey getId() {
        return id;
    }

    public void setId(PreceptorStudentKey id) {
        this.id = id;
    }

    public Preceptor getPreceptor() {
        return preceptor;
    }

    public void setPreceptor(Preceptor preceptor) {
        this.preceptor = preceptor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
